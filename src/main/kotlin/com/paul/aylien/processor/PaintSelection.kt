package com.paul.aylien.processor

import com.paul.aylien.input.Color
import com.paul.aylien.input.Finish
import com.paul.aylien.input.Paint


class PaintSelection private constructor(private val paints: Map<Color, Finish>,
                                         val numberOfMatteFinish: Int) {

    constructor() : this(mapOf(), 0)

    fun getNeededFinishFor(color: Color): Finish = paints.getOrDefault(color, Finish.GLOSSY)

    fun plusPaint(paint: Paint): PaintSelection = PaintSelection(
            paints.plus(paint.color.to(paint.finish)),
            newMatteCount(numberOfMatteFinish, paint))

    private fun newMatteCount(countOfMatteFinish: Int, paint: Paint): Int =
            countOfMatteFinish +
                    if (paint.finish == Finish.MATTE) {
                        1
                    } else {
                        0
                    }

    fun isColorFree(color: Color): Boolean = paints[color] == null
    fun getColorFinish(color: Color): Finish? = paints[color]


    operator fun <K, V> Map<out K, V>.plus(pair: Pair<K, V>): Map<K, V>
            = if (this.isEmpty()) mapOf(pair) else HashMap(this).apply { put(pair.first, pair.second) }


    override fun toString() = paints.toString()
}