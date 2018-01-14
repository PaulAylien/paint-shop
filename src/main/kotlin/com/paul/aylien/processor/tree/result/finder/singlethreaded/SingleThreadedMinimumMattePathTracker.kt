package com.paul.aylien.processor.tree.result.finder.singlethreaded

import com.paul.aylien.processor.tree.PaintPreferenceNode
import com.paul.aylien.processor.tree.result.SuccessfulCombination
import com.paul.aylien.processor.tree.result.TreePathResult
import com.paul.aylien.processor.tree.result.finder.MinimumMattePathTracker


class SingleThreadedMinimumMattePathTracker : MinimumMattePathTracker {

    private var lowestNumberOfMatteUsedInOtherBranch = Integer.MAX_VALUE

    override fun isAboveOrEqualToMinimum(paintSelection: PaintPreferenceNode): Boolean = lowestNumberOfMatteUsedInOtherBranch <= paintSelection.numberOfMatteFinish

    override fun newBaseCase(new: TreePathResult) {
        when (new) {
            is SuccessfulCombination -> lowestNumberOfMatteUsedInOtherBranch = new.finishes.numberOfMatteFinish
        }
    }
}