package com.paul.aylien.processor.tree

import com.paul.aylien.input.Customer
import com.paul.aylien.processor.DefaultPathLogger
import com.paul.aylien.processor.PaintSelection
import com.paul.aylien.processor.PaintSelectionGenerator
import com.paul.aylien.processor.PathLogger


class BestPathFinder(private val pathLogger: PathLogger = DefaultPathLogger(),
                     private val allOriginalCustomers: List<Customer>) {


    private val paintSelectionGenerator = PaintSelectionGenerator()
    private val minimumMattePathTracker = MinimumMattePathTracker()

    fun findBestPath() = findBestPathRecursive(allOriginalCustomers, PaintSelection())


    private fun findBestPathRecursive(customers: List<Customer>,
                                      consumedPaints: PaintSelection): TreePathResult =
            when {
                minimumMattePathTracker.isAboveOrEqualToMinimum(consumedPaints) -> newBaseCase(NotBestChoice())
                customers.isEmpty() -> newBaseCase(SuccessfulCombination(consumedPaints))
                else -> findBestBatchFor(customers, consumedPaints)
            }

    private fun newBaseCase(treePathResult: TreePathResult): TreePathResult {
        minimumMattePathTracker.newBaseCase(treePathResult)
        pathLogger.log(treePathResult)
        return treePathResult
    }

    private fun findBestBatchFor(customers: List<Customer>,
                                 currentPaintSelection: PaintSelection): TreePathResult {
        val newPaintSelections = paintSelectionGenerator.generateSelection(customers, currentPaintSelection)
        return newPaintSelections
                .map { findBestPathRecursive(customers.tail(), it) }
                .filterIsInstance<SuccessfulCombination>()
                .firstOrNull() ?: newBaseCase(NotBestChoice())
    }
}


fun <T> List<T>.tail() = subList(1, size)
