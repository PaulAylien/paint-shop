package com.paul.aylien.input


class InputLines(private val iterator: Iterator<String>) {

    fun next(): String = iterator.next()

    fun nextInt(intDescription: String): Int = try {
        iterator.next().toInt()
    } catch (e: Exception) {
        throw RuntimeException("error reading " + intDescription)
    }

    fun next(numberOfLines: Int): List<String> =
            (1..numberOfLines).map { iterator.next() }
}