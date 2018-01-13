package com.paul.aylien

import com.paul.aylien.processor.DefaultPathLogger
import com.paul.aylien.processor.PathLogger
import com.paul.aylien.processor.tree.TreePathResult


class TestPathLogger : PathLogger {

    val receivedEvents = mutableListOf<TreePathResult>()
    val defaultPathLogger = DefaultPathLogger()

    override fun log(treePathResult: TreePathResult) {
        defaultPathLogger.log(treePathResult)
        receivedEvents.add(treePathResult)
    }

}