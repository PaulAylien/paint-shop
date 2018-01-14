package com.paul.aylien.input

import java.util.*

/**
 * Data objects which represent the test case input
 */

data class Paint(val color: Color,
                 val finish: Finish) : Comparable<Paint> {

    override fun compareTo(other: Paint): Int =
            when (this.finish) {
                other.finish -> color.compareTo(other.color)
                Finish.MATTE -> 1
                Finish.GLOSSY -> -1
            }
}

data class Color(val value: Int) : Comparable<Color> {

    override fun compareTo(other: Color): Int =
            this.value.compareTo(other.value)
}

enum class Finish {
    GLOSSY,
    MATTE
}

data class Customer(val preferences: SortedSet<Paint>)

data class TestCase(val customers: List<Customer>, val numberOfPaints: Int)




