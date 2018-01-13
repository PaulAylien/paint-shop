package com.paul.aylien.input


class TestCaseBuilder {

    private val customerLineInputReader = CustomerBuilder()

    fun createTestCases(lines: InputLines): List<TestCase> {
        val numberOfTestCases = lines.nextInt("numberOfTestCases")
        return (1..numberOfTestCases)
                .map { createTestCase(lines) }

    }


    private fun createTestCase(lineSequence: InputLines): TestCase {
        val numberOfPaints = lineSequence.nextInt("numberOfPaints")
        val numberOfCustomers = lineSequence.nextInt("numberOfCustomers")

        val customers = lineSequence.next(numberOfCustomers)
                .map { customerLineInputReader.createCustomer(it) }
        return TestCase(customers.toList(), numberOfPaints)
    }

}