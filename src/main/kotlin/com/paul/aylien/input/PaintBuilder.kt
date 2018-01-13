package com.paul.aylien.input



/**
 * Responsible for building a Paint object from a pair of ints
 * e.g.
 * 1 1 ->  Paint(color=1,Finish=MATTE)
 * 5 0  -> Paint(color=5,Finish=GLOSSY)
 */
class PaintBuilder {

    fun createPaint(colorInput: Pair<Int, Int>): Paint =
            Paint(Color(colorInput.first), getFinish(colorInput.second))

    private fun getFinish(i: Int) =
            when (i) {
                0 -> Finish.GLOSSY
                1 -> Finish.MATTE
                else -> throw IllegalArgumentException("file is not valid")
            }
}