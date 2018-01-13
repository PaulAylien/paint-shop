package com.paul.aylien.output

import com.paul.aylien.input.Finish


sealed class Result
class Impossible : Result() {
    override fun equals(other: Any?): Boolean = other != null && other is Impossible
    override fun hashCode(): Int = 1
}

data class SuccessfulResult(val finishes: List<Finish>) : Result()