package com.paul.aylien.processor.tree.result.finder.multithreaded

import com.paul.aylien.processor.tree.PaintPreferenceNode
import com.paul.aylien.processor.tree.result.SuccessfulCombination
import com.paul.aylien.processor.tree.result.TreePathResult
import com.paul.aylien.processor.tree.result.finder.MinimumMattePathTracker
import java.util.concurrent.atomic.AtomicInteger


class MultiThreadedMinimumMattePathTracker : MinimumMattePathTracker {

    private var lowestNumberOfMatteUsedInOtherBranch = AtomicInteger(Integer.MAX_VALUE)

    override fun isAboveOrEqualToMinimum(paintSelection: PaintPreferenceNode): Boolean = lowestNumberOfMatteUsedInOtherBranch.get() <= paintSelection.numberOfMatteFinish

    override fun newBaseCase(new: TreePathResult) {
        when (new) {
            is SuccessfulCombination -> {
                val get = lowestNumberOfMatteUsedInOtherBranch.get()
                if (get > new.finishes.numberOfMatteFinish) {
                    if (!lowestNumberOfMatteUsedInOtherBranch.compareAndSet(get, new.finishes.numberOfMatteFinish)) {
                        trySetLowest(new.finishes.numberOfMatteFinish)
                    }
                }
            }
        }
    }

    private fun trySetLowest(numberOfMatteFinish: Int) {
        val get = lowestNumberOfMatteUsedInOtherBranch.get()
        if (get > numberOfMatteFinish) {
            if (!lowestNumberOfMatteUsedInOtherBranch.compareAndSet(get, numberOfMatteFinish)) {
                trySetLowest(numberOfMatteFinish)
            }
        }
    }
}