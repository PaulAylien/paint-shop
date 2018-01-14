package com.paul.aylien.generator

import com.paul.aylien.input.Color
import java.util.*

/**
 * A utility class for generating colours.
 * Used by InputGenerator.kt
 */
class RandomColoursGenerator(private val numberOfPaints: Int,
                             private val numberOfCustomers: Int,
                             private val maximumT: Int = 3000) {

    fun generateColoursForCustomers(): List<Color> {
        val numberOfPreferences = (1..numberOfPaints).random()

        val averageNumberOfPaintsLikedByCustomer = maximumT / numberOfCustomers

        val numberOfPreferencesPerCustomer = Math.min(numberOfPreferences, averageNumberOfPaintsLikedByCustomer)

        val r = Random()
        val map = (1..numberOfPaints).map { it }

        return (1..numberOfPreferencesPerCustomer)
                .map { map[r.nextInt(numberOfPaints)] }.map { Color(it) }

    }
}