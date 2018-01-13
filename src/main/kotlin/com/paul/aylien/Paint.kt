package com.paul.aylien

import com.paul.aylien.input.ConsoleInputProvider
import com.paul.aylien.output.ConsolerResultReporter
import com.paul.aylien.processor.DefaultPaintMixDecisionMaker


fun main(args: Array<String>) {

    val defaultInputParser = ConsoleInputProvider()
    val paintMixDecisionMaker = DefaultPaintMixDecisionMaker()
    val resultReporter = ConsolerResultReporter()


    val testCases = defaultInputParser.getTestCases()
    testCases.map { paintMixDecisionMaker.findBestBatch(it) }
            .map { resultReporter.reportResult(it) }

}





