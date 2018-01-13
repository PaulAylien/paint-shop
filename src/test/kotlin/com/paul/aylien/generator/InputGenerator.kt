package com.paul.aylien.generator

import com.paul.aylien.input.Customer
import java.io.File
import java.util.*


fun main(args: Array<String>) {
    publishBatch(label = "smallbatch.txt",
            numberOfTestCases = 100,
            maximumNumberOfPaints = 10,
            maximumNumberOfCustomers = 100)

    publishBatch(label = "bigbatch.txt",
            numberOfTestCases = 5,
            maximumNumberOfPaints = 2000,
            maximumNumberOfCustomers = 2000)
}

private fun publishBatch(label: String, numberOfTestCases: Int, maximumNumberOfPaints: Int, maximumNumberOfCustomers: Int) {
    File(label).printWriter().use { out ->
        out.println(numberOfTestCases)
        (1..numberOfTestCases).forEach {

            val numberOfPaints = (1..maximumNumberOfPaints).random()
            val numberOfCustomers = (1..maximumNumberOfCustomers).random()
            out.println(numberOfPaints)
            out.println(numberOfCustomers)
            RandomCustomersGenerator(numberOfPaints, numberOfCustomers).generateCustomers()
                    .map { customerToString(it) }
                    .forEach { out.println(it) }
        }
    }
}

private fun customerToString(it: Customer) =
        it.preferences.joinToString(prefix = "${it.preferences.size} ", separator = " ", transform = { "${it.color.value} ${it.finish.ordinal}" })

fun ClosedRange<Int>.random() = Random().nextInt(endInclusive) + 1