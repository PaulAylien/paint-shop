package com.paul.aylien

import com.paul.aylien.processor.tree.logging.DefaultPathLogger
import com.paul.aylien.processor.tree.logging.PathLogger
import com.paul.aylien.processor.tree.result.TreePathResult

/**
 * when testing the TestPathLogger can be used to track exactly how many failures are occurring.
 * Very useful for performance testing.
 */
class TestPathLogger : PathLogger {

    val receivedEvents = mutableListOf<TreePathResult>()
    val defaultPathLogger = DefaultPathLogger()

    override fun log(treePathResult: TreePathResult) {
        defaultPathLogger.log(treePathResult)
        receivedEvents.add(treePathResult)
    }

}