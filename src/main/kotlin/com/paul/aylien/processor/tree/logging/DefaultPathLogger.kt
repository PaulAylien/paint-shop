package com.paul.aylien.processor.tree.logging

import com.paul.aylien.processor.tree.result.TreePathResult
import mu.KLogging


class DefaultPathLogger : PathLogger {

    companion object : KLogging()

    override fun log(treePathResult: TreePathResult) =
            logger.info { "received $treePathResult" }

}