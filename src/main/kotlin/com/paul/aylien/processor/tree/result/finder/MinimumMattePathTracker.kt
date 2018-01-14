package com.paul.aylien.processor.tree.result.finder

import com.paul.aylien.processor.tree.PaintPreferenceNode
import com.paul.aylien.processor.tree.result.SuccessfulCombination
import com.paul.aylien.processor.tree.result.TreePathResult

/**
 * A stateful object which keeps track of the solution with the lowest matte count.
 *
 * This class is used to short circuit the evaluation of paths(branches) with matte counts greater than or eqaul to the current best solutions matte counts.
 */
class MinimumMattePathTracker {

    private var lowestNumberOfMatteUsedInOtherBranch = Integer.MAX_VALUE

    fun isAboveOrEqualToMinimum(paintSelection: PaintPreferenceNode): Boolean = lowestNumberOfMatteUsedInOtherBranch <= paintSelection.numberOfMatteFinish

    fun newBaseCase(new: TreePathResult) {
        when (new) {
            is SuccessfulCombination -> lowestNumberOfMatteUsedInOtherBranch = new.finishes.numberOfMatteFinish
        }
    }


}