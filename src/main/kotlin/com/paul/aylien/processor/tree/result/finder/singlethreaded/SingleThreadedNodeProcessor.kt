package com.paul.aylien.processor.tree.result.finder.singlethreaded

import com.paul.aylien.input.Customer
import com.paul.aylien.processor.tree.PaintPreferenceNode
import com.paul.aylien.processor.tree.result.TreePathResult
import com.paul.aylien.processor.tree.result.finder.NodeProcessor

class SingleThreadedNodeProcessor : NodeProcessor {

    override fun processNodes(children: List<PaintPreferenceNode>,
                              branchResultProvider: (PaintPreferenceNode) -> TreePathResult): List<TreePathResult> =
        children.map { branchResultProvider.invoke(it) }


}