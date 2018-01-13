package com.paul.aylien.output


class ConsolerResultReporter : ResultReporter {


    override fun reportResult(result: Result) =
            println(getResultString(result))

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