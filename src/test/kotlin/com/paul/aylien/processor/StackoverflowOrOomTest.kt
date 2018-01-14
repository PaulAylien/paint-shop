package com.paul.aylien.processor

import com.paul.aylien.TestPathLogger
import com.paul.aylien.TestUtils
import com.paul.aylien.input.ConsoleInputProvider
import com.paul.aylien.processor.tree.result.finder.BestPathFinderFactory
import org.junit.jupiter.api.Test


internal class StackoverflowOrOomTest {

    private val logger = TestPathLogger()
    private val defaultPaintMixDecisionMaker = DefaultPaintMixDecisionMaker(BestPathFinderFactory(logger))


    @Test
    fun `test it with a big batch`() {
        // arrange
        TestUtils.setFileContentAsSystemIn("bigbatch.txt")
        // act
        ConsoleInputProvider().getTestCases()
                .map { defaultPaintMixDecisionMaker.findBestBatch(it) }
        //assert
        // assuming the logic is correct this is just checking there are no stackoverflow or oom errors
    }

}