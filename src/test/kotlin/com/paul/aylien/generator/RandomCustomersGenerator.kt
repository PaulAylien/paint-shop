package com.paul.aylien.generator

import com.paul.aylien.input.Color
import com.paul.aylien.input.Customer
import com.paul.aylien.input.Finish
import com.paul.aylien.input.Paint
import java.util.*

/**
 * A utility class for generating colours.
 * Used by InputGenerator.kt
 */
class RandomCustomersGenerator(numberOfPaints: Int, private val numberOfCustomers: Int) {

    private val randomColoursGenerator: RandomColoursGenerator = RandomColoursGenerator(numberOfPaints, numberOfCustomers)

    fun generateCustomers(): List<Customer> =
            (1..numberOfCustomers)
                    .map { generateCustomer() }
                    .map { Customer(it.toSortedSet()) }


    private fun generateCustomer(): List<Paint> {
        val coloursForCustomers = randomColoursGenerator.generateColoursForCustomers()

        return listOf(generatePotentiallyMattePaint(coloursForCustomers[0]))
                .plus(coloursForCustomers.subList(1, coloursForCustomers.size).map { Paint(it, Finish.GLOSSY) })
    }


    private fun generatePotentiallyMattePaint(colour: Color) =
            Paint(colour, nextBoolean().let { if (it) Finish.MATTE else Finish.GLOSSY })
}


private fun nextBoolean() = Random().nextBoolean()