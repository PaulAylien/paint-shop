package com.paul.aylien.processor.tree.logging

import com.paul.aylien.processor.tree.result.TreePathResult

/**
 * Responsible for logging the occurrence of TreePathResult events.
 *
 * The default implementation logs to a logger
 *
 * However when testing the TestPathLogger can be used to track exactly how many failures are occurring.
 * Very useful for performance testing.
 *
 */
interface PathLogger {

    fun log(treePathResult: TreePathResult)
}