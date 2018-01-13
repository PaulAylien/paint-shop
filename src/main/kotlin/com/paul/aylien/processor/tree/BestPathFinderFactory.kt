package com.paul.aylien.processor.tree

import com.paul.aylien.input.Customer
import com.paul.aylien.processor.DefaultPathLogger
import com.paul.aylien.processor.PathLogger

class BestPathFinderFactory(private val logger: PathLogger = DefaultPathLogger()) {

    fun newBestPathFinder(customers: List<Customer>): BestPathFinder = BestPathFinder(logger, customers)
}