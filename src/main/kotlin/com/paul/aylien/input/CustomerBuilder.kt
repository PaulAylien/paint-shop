package com.paul.aylien.input

import java.util.*


/**
 * Responsible for building a customer from a line
 * e.g.
 * 1 1 1  ->  Customer(Set(Paint(color=1,Finish=MATTE)))
 * 1 1 1 5 0  ->  Customer(Set(Paint(color=1,Finish=MATTE),Paint(color=5,Finish=GLOSSY)))
 */
class CustomerBuilder {

    private val paintBuilder = PaintBuilder()

    fun createCustomer(line: String): Customer {
        val lines = line.trim().split(" ")
                .map { it.toInt() }.iterator()
        val numberOfColorsLikedByCustomer = lines.next()
        return Customer(getLikedPaints(numberOfColorsLikedByCustomer, lines))
    }

    private fun getLikedPaints(numberOfColorsLikedByCustomer: Int, lines: Iterator<Int>): SortedSet<Paint> {
        return (1..numberOfColorsLikedByCustomer)
                .map { lines.next().to(lines.next()) }
                .map { paintBuilder.createPaint(it) }.toSortedSet()
    }

}
