package com.paul.aylien.processor.tree.result.finder.multithreaded

import com.paul.aylien.input.Customer
import com.paul.aylien.processor.tree.result.TreePathResult
import com.paul.aylien.processor.tree.result.finder.NodeProcessor
import com.paul.aylien.processor.tree.result.finder.singlethreaded.SingleThreadedNodeProcessor
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking


const val THREAD_SPAWNING_DEFAULT = 100 // if there are more than this number of customers a new thread will be spawned.

class MultiThreadedNodeProcessor(private val threadSpawningCount: Int = THREAD_SPAWNING_DEFAULT) : NodeProcessor {

    private val singleThreadedNodeProcessor = SingleThreadedNodeProcessor()

    override fun process(customers: List<Customer>,
                         numberOfBranches: Int,
                         branchResultProvider: () -> TreePathResult): TreePathResult =
            if (customers.size > threadSpawningCount && numberOfBranches > 2) {
                runBlocking {
                    val async = async(CommonPool) { branchResultProvider.invoke() }
                    async.await()
                }
            } else {
                singleThreadedNodeProcessor.process(customers, numberOfBranches, branchResultProvider)
            }

}