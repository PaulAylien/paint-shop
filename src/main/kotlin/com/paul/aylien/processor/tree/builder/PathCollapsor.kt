package com.paul.aylien.processor.tree.builder

import com.paul.aylien.input.Paint
import com.paul.aylien.processor.tree.CachingNode
import com.paul.aylien.processor.tree.PaintPreferenceNode
import com.paul.aylien.processor.tree.Root
import com.paul.aylien.processor.tree.StandardNode

/**
 * Related to optimization 7.
 *
 *
 */
object PathCollapsor {

    fun collapseToCacheNode(me: PaintPreferenceNode, paint: Paint): CachingNode {
        val collapseParent = collapseParent(me)
        collapseParent.paints.add(paint)
        return CachingNode(collapseParent.parent,
                collapseParent.paints.map { it.color.to(it.finish) }.toMap(),
                collapseParent.numberOfMatteFinish)
    }

    private fun collapseParent(me: PaintPreferenceNode): CollapsingParentAccumulation =
            when (me) {
                is Root -> CollapsingParentAccumulation(mutableListOf(), 0, me)
                is CachingNode -> CollapsingParentAccumulation(mutableListOf(), 0, me)
                is StandardNode -> accumulateThisWithParent(collapseParent(me.parent), me)
            }


    private fun accumulateThisWithParent(parentSummary: CollapsingParentAccumulation, me: StandardNode): CollapsingParentAccumulation {
        parentSummary.paints.add(me.paint)
        parentSummary.numberOfMatteFinish += me.paint.finish.ordinal
        return parentSummary
    }


    private data class CollapsingParentAccumulation(val paints: MutableList<Paint>,
                                                    var numberOfMatteFinish: Int,
                                                    val parent: PaintPreferenceNode)


    private operator fun <K, V> Map<out K, V>.plus(pair: Pair<K, V>): Map<K, V>
            = if (this.isEmpty()) mapOf(pair) else HashMap(this).apply { put(pair.first, pair.second) }
}