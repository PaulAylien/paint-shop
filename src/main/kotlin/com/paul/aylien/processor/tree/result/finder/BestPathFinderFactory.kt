package com.paul.aylien.processor.tree.result.finder

import com.paul.aylien.input.Customer
import com.paul.aylien.processor.tree.logging.DefaultPathLogger
import com.paul.aylien.processor.tree.logging.PathLogger
import com.paul.aylien.processor.tree.result.finder.singlethreaded.SingleThreadedMinimumMattePathTracker
import com.paul.aylien.processor.tree.result.finder.singlethreaded.SingleThreadedNodeProcessor

class BestPathFinderFactory(private val logger: PathLogger = DefaultPathLogger()) {

    fun newBestPathFinder(customers: List<Customer>): BestPathFinder = BestPathFinder(logger,
            sort(customers), SingleThreadedNodeProcessor(), SingleThreadedMinimumMattePathTracker())


    //https://github.com/PaulAylien/paint-shop#optimization-6-sort-the-customers-based-on-number-of-preferences-before-processing
    private fun sort(customers: List<Customer>) =
            customers.sortedBy { it.preferences.size }
}