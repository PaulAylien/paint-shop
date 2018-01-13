package com.paul.aylien.processor

import com.paul.aylien.input.Color
import com.paul.aylien.input.TestCase
import com.paul.aylien.output.Impossible
import com.paul.aylien.output.Result
import com.paul.aylien.output.SuccessfulResult
import com.paul.aylien.processor.tree.BestPathFinderFactory
import com.paul.aylien.processor.tree.NotBestChoice
import com.paul.aylien.processor.tree.SuccessfulCombination

class DefaultPaintMixDecisionMaker(private val bestPathFinderFactory: BestPathFinderFactory) : PaintMixDecisionMaker {


    override fun findBestBatch(testCase: TestCase): Result {
        val newBestPathFinder = bestPathFinderFactory.newBestPathFinder(testCase.customers)
        val findColorsNeeded = newBestPathFinder.findBestPath()
        return when (findColorsNeeded) {
            is SuccessfulCombination -> SuccessfulResult((1..testCase.numberOfPaints)
                    .map { Color(it) }
                    .map { findColorsNeeded.finishes.getNeededFinishFor(it) })
            is NotBestChoice -> Impossible()
        }
    }

}