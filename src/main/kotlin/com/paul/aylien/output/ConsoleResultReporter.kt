package com.paul.aylien.output

/**
 * Responsible for reporting results to the console.
 */
class ConsoleResultReporter : ResultReporter {


    override fun reportResults(results: List<Result>) =
            results.forEachIndexed { index, result -> println("Case #${index+1}: ${getResultString(result)}") }

    private fun getResultString(result: Result): String {
        return when (result) {
            is SuccessfulResult -> result
                    .finishes
                    .map { it.ordinal }
                    .joinToString()
            is Impossible -> "IMPOSSIBLE"
        }
    }
}