package com.paul.aylien.processor.tree.result

import com.paul.aylien.processor.tree.PaintPreferenceNode

/**
 * Represents the result of processing a branch in the tree.
 *
 * A branch can either be NotBestChoice or SuccessfulCombination
 */
sealed class TreePathResult

class NotBestChoice : TreePathResult() {
    override fun equals(other: Any?): Boolean = other != null && other is NotBestChoice
    override fun hashCode(): Int = 1
}

data class SuccessfulCombination(val finishes: PaintPreferenceNode) : TreePathResult()