package com.paul.aylien.processor.tree.result.finder

import com.paul.aylien.input.Customer
import com.paul.aylien.processor.LeafNodeBuilder
import com.paul.aylien.processor.tree.PaintPreferenceNode
import com.paul.aylien.processor.tree.Root
import com.paul.aylien.processor.tree.logging.DefaultPathLogger
import com.paul.aylien.processor.tree.logging.PathLogger
import com.paul.aylien.processor.tree.result.NotBestChoice
import com.paul.aylien.processor.tree.result.SuccessfulCombination
import com.paul.aylien.processor.tree.result.TreePathResult
import com.paul.aylien.processor.tree.result.finder.singlethreaded.SingleThreadedMinimumMattePathTracker

/**
 * Responsible for finding the best solution to the problem.
 *
 * XXX The object is stateful because of MinimumMattePathTracker, a new one needs to be created for each testcase.
 */
class BestPathFinder(private val pathLogger: PathLogger = DefaultPathLogger(),
                     private val allOriginalCustomers: List<Customer>,
                     private val nodeProcessor: NodeProcessor,
                     private val minimumMattePathTracker: MinimumMattePathTracker = SingleThreadedMinimumMattePathTracker()) {

    private val leafNodeBuilder = LeafNodeBuilder()


    fun findBestPath() = findBestPathRecursive(allOriginalCustomers, Root())


    private fun findBestPathRecursive(customers: List<Customer>,
                                      consumedPaints: PaintPreferenceNode): TreePathResult =
            when {
            // base cases
                minimumMattePathTracker.isAboveOrEqualToMinimum(consumedPaints) -> newBaseCase(NotBestChoice())
                customers.isEmpty() -> newBaseCase(SuccessfulCombination(consumedPaints))
            // recurse
                else -> findBestBatchFor(customers, consumedPaints)
            }

    private fun newBaseCase(treePathResult: TreePathResult): TreePathResult {
        minimumMattePathTracker.newBaseCase(treePathResult)
        pathLogger.log(treePathResult)
        return treePathResult
    }

    private fun findBestBatchFor(customers: List<Customer>,
                                 currentPaintSelection: PaintPreferenceNode): TreePathResult {
        val newPaintSelections = leafNodeBuilder.generateSelection(customers.first(), currentPaintSelection)
        return nodeProcessor.processNodes(newPaintSelections,
                {paintSelection -> findBestPathRecursive(customers.tail() ,paintSelection )})
                .filterIsInstance<SuccessfulCombination>()
                .firstOrNull() ?: newBaseCase(NotBestChoice())
    }
}


fun <T> List<T>.tail() = subList(1, size)
