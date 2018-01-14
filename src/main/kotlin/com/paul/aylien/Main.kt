package com.paul.aylien

import com.paul.aylien.input.ConsoleInputProvider
import com.paul.aylien.output.ConsoleResultReporter
import com.paul.aylien.processor.DefaultPaintMixDecisionMaker
import com.paul.aylien.processor.tree.result.finder.BestPathFinderFactory


fun main(args: Array<String>) {

    val defaultInputParser = ConsoleInputProvider()
    val paintMixDecisionMaker = DefaultPaintMixDecisionMaker(BestPathFinderFactory())
    val resultReporter = ConsoleResultReporter()


    val testCases = defaultInputParser.getTestCases()
    val results = testCases.map { paintMixDecisionMaker.findBestBatch(it) }
    resultReporter.reportResults(results)

}





