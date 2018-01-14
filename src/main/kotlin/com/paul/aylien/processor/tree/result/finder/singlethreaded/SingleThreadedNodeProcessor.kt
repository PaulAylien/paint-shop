package com.paul.aylien.processor.tree.result.finder.singlethreaded

import com.paul.aylien.input.Customer
import com.paul.aylien.processor.tree.result.TreePathResult
import com.paul.aylien.processor.tree.result.finder.NodeProcessor

class SingleThreadedNodeProcessor : NodeProcessor {
    override fun process(customers: List<Customer>,
                         numberOfBranches: Int,
                         branchResultProvider: () -> TreePathResult): TreePathResult = branchResultProvider.invoke()

}