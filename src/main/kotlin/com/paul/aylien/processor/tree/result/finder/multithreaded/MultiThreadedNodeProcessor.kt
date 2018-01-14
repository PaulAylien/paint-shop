package com.paul.aylien.processor.tree.result.finder.multithreaded

import com.paul.aylien.input.Customer
import com.paul.aylien.processor.tree.PaintPreferenceNode
import com.paul.aylien.processor.tree.result.TreePathResult
import com.paul.aylien.processor.tree.result.finder.NodeProcessor
import com.paul.aylien.processor.tree.result.finder.singlethreaded.SingleThreadedNodeProcessor
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.NonCancellable.children
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking



class MultiThreadedNodeProcessor(private val threadSpawningCount: Int = 4) : NodeProcessor {

    private var hasSplit : Boolean = false
    private val singleThreadedNodeProcessor = SingleThreadedNodeProcessor()


    override fun processNodes(children: List<PaintPreferenceNode>,
                              branchResultProvider: (PaintPreferenceNode) -> TreePathResult): List<TreePathResult> =
        if (children.size >= threadSpawningCount && !hasSplit) {
            hasSplit = true
            runBlocking {
                       children.map { async(CommonPool) { branchResultProvider.invoke(it) } }
                               .map {  it.await() }

            }
        } else {
            singleThreadedNodeProcessor.processNodes(children,branchResultProvider)
        }
}