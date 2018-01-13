package com.paul.aylien.processor.tree

import com.paul.aylien.processor.PaintSelection


class MinimumMattePathTracker {

    private var lowestNumberOfMatteUsedInOtherBranch = Integer.MAX_VALUE

    fun isAboveOrEqualToMinimum(paintSelection: PaintSelection): Boolean = lowestNumberOfMatteUsedInOtherBranch <= paintSelection.numberOfMatteFinish

    fun newBaseCase(new: TreePathResult) {
        when (new) {
            is SuccessfulCombination -> lowestNumberOfMatteUsedInOtherBranch = new.finishes.numberOfMatteFinish
        }
    }


}