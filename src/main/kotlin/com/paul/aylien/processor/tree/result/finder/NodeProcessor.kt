package com.paul.aylien.processor.tree.result.finder

import com.paul.aylien.input.Customer
import com.paul.aylien.processor.tree.PaintPreferenceNode
import com.paul.aylien.processor.tree.result.TreePathResult

/**
 * Responsible for deciding how all the children of a node should be processed.
 *
 * 2 implementations :
 * 1. SingleThreadedNodeProcessor = always runs the evaluation of the children in the same thread
 * 2. Runs the evaluation of the children in separate threads
 *
 */
interface NodeProcessor {

    /**
     * @param branchResultProvider the operation to run on each child node
     */
    fun  processNodes(children : List<PaintPreferenceNode>,
                      branchResultProvider: (PaintPreferenceNode) -> TreePathResult): List<TreePathResult>
}

