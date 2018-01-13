package com.paul.aylien.processor

import com.paul.aylien.input.TestCase
import com.paul.aylien.output.Result


interface PaintMixDecisionMaker {
    fun findBestBatch(testCase: TestCase): Result
}