package com.paul.aylien.input

/**
 * Provides a list of test-cases from an unspecified source
 *
 * At the moment there is only a single implementation, the consoleInputProvider which reads test cases from the console.
 */
interface InputProvider {
    fun getTestCases(): List<TestCase>
}