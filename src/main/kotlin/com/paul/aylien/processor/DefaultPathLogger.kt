package com.paul.aylien.processor

import com.paul.aylien.processor.tree.TreePathResult
import mu.KLogging


class DefaultPathLogger : PathLogger {

    companion object : KLogging()

    override fun log(treePathResult: TreePathResult) =
            logger.info { "received $treePathResult" }

}