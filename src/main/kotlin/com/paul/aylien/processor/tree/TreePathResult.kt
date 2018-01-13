package com.paul.aylien.processor.tree

import com.paul.aylien.processor.PaintSelection

/**
 *
 */
sealed class TreePathResult

class NotBestChoice : TreePathResult() {
    override fun equals(other: Any?): Boolean = other != null && other is NotBestChoice
    override fun hashCode(): Int = 1
}

data class SuccessfulCombination(val finishes: PaintSelection) : TreePathResult()