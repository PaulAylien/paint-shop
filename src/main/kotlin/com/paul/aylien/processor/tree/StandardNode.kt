package com.paul.aylien.processor.tree

import com.paul.aylien.input.Color
import com.paul.aylien.input.Finish
import com.paul.aylien.input.Paint
import com.paul.aylien.processor.tree.builder.PathCollapsor.collapseToCacheNode


const val NUMBER_OF_NODES_IN_PATH_BEFORE_CACHING = 100

/**
 * Represents a node in the tree.
 * There are 3 different implementations
 *
 * 1. Root == represents the root node
 * 2. StandardNode == represents a standard node
 * 3. CachingNode == Used for optimization 7
 */
sealed class PaintPreferenceNode(val numberOfMatteFinish: Int) {
    abstract fun getColorFinish(color: Color): Finish?
    fun isColorFree(color: Color): Boolean = getColorFinish(color) == null
    fun getNeededFinishFor(color: Color): Finish = getColorFinish(color) ?: Finish.GLOSSY

    /**
     * Creates a new leaf node from the this node and the new paint
     */
    abstract fun newLeafNodePathWith(paint: Paint): PaintPreferenceNode
}

class Root : PaintPreferenceNode(0) {
    override fun newLeafNodePathWith(paint: Paint): StandardNode = StandardNode(this, paint, 1, paint.finish.ordinal)
    override fun getColorFinish(color: Color): Finish? = null

}

class StandardNode(val parent: PaintPreferenceNode,
                   val paint: Paint,
                   private val numberOfUnCached: Int,
                   numberOfMatteFinish: Int) : PaintPreferenceNode(numberOfMatteFinish) {

    override fun newLeafNodePathWith(paint: Paint): PaintPreferenceNode =
            if (numberOfUnCached > NUMBER_OF_NODES_IN_PATH_BEFORE_CACHING) {
                collapseToCacheNode(this, paint)
            } else {
                StandardNode(this, paint, numberOfUnCached + 1, numberOfMatteFinish + paint.finish.ordinal)
            }

    override fun getColorFinish(color: Color): Finish? =
            if (paint.color == color)
                paint.finish
            else
                parent.getColorFinish(color)
}


class CachingNode(private val parent: PaintPreferenceNode,
                  private val paints: Map<Color, Finish>,
                  numberOfMatteFinish: Int) : PaintPreferenceNode(numberOfMatteFinish) {

    override fun getColorFinish(color: Color): Finish? = paints[color] ?: parent.getColorFinish(color)

    override fun newLeafNodePathWith(paint: Paint): StandardNode =
            StandardNode(this, paint, 1, numberOfMatteFinish)
}


