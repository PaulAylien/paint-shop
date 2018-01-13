package com.paul.aylien.input


data class Paint(val color: Color,
                 val finish: Finish)

data class Color(val value: Int) : Comparable<Color> {

    override fun compareTo(other: Color): Int =
            this.value.compareTo(other.value)
}

enum class Finish {
    GLOSSY,
    MATTE
}

fun matteHasLowestPriority(): java.util.Comparator<Paint> {
    return Comparator { a, b ->
        when (a.finish) {
            b.finish -> 0
            Finish.MATTE -> 1
            Finish.GLOSSY -> -1
        }
    }
}

data class Customer(val preferences: Set<Paint>)

data class TestCase(val customers: List<Customer>, val numberOfPaints: Int)




