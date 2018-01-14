package com.paul.aylien.processor.tree.result.finder

import com.paul.aylien.processor.tree.PaintPreferenceNode
import com.paul.aylien.processor.tree.result.TreePathResult

/**
 * A stateful object which keeps track of the solution with the lowest matte count.
 *
 * This class is used to short circuit the evaluation of paths(branches) with matte counts greater than or eqaul to the current best solutions matte counts.
 */
interface MinimumMattePathTracker {

    fun isAboveOrEqualToMinimum(paintSelection: PaintPreferenceNode): Boolean

    fun newBaseCase(new: TreePathResult)
}