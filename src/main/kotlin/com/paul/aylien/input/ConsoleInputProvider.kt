package com.paul.aylien.input

/**
 * reads tests cases from the console
 */
class ConsoleInputProvider : InputProvider {

    override fun getTestCases(): List<TestCase> =
            System.`in`.bufferedReader().use {
                val lines = it.lineSequence().iterator()
                TestCaseBuilder().createTestCases(InputLines(lines))
            }

}