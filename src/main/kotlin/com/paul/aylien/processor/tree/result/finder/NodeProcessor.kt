package com.paul.aylien.processor.tree.result.finder

import com.paul.aylien.input.Customer
import com.paul.aylien.processor.tree.result.TreePathResult

/**
 * Responsible for deciding how all the children of a node should be processed.
 *
 * 2 implementations :
 * 1. SingleThreadedNodeProcessor = always runs the evaluation of the children in the same thread
 * 2. Runs the evaluation of the children in separate threads if there are still a large amount of customers to process and this node has many branches
 *
 */
interface NodeProcessor {
    fun process(customers: List<Customer>,
                numberOfBranches: Int,
                branchResultProvider: () -> TreePathResult): TreePathResult
}

