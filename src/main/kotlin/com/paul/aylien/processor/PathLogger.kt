package com.paul.aylien.processor

import com.paul.aylien.processor.tree.TreePathResult


interface PathLogger {

    fun log(treePathResult: TreePathResult)
}