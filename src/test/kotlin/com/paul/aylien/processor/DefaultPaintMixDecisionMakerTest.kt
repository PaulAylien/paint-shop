package com.paul.aylien.processor

import com.paul.aylien.TestPathLogger
import com.paul.aylien.TestUtils.setFileContentAsSystemIn
import com.paul.aylien.input.*
import com.paul.aylien.input.Finish.GLOSSY
import com.paul.aylien.input.Finish.MATTE
import com.paul.aylien.output.Impossible
import com.paul.aylien.output.SuccessfulResult
import com.paul.aylien.processor.tree.result.SuccessfulCombination
import com.paul.aylien.processor.tree.result.finder.BestPathFinderFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class DefaultPaintMixDecisionMakerTest {

    private val logger = TestPathLogger()
    private val defaultPaintMixDecisionMaker = DefaultPaintMixDecisionMaker(BestPathFinderFactory(logger))

    @Test
    fun `As soon as a successful solution with 0 matte paints is found no other paths in the tree are tried`() {
        val paints = sortedSetOf(
                Paint(Color(1), GLOSSY),
                Paint(Color(2), GLOSSY),
                Paint(Color(3), GLOSSY))
        val testCase = TestCase(listOf(
                Customer(paints)), 10)

        defaultPaintMixDecisionMaker.findBestBatch(testCase)

        Assertions.assertEquals(1, logger.receivedEvents.filter { it is SuccessfulCombination }.size)
    }

    @Test
    fun `example test case from provided document`() {
        // arrange
        val file = "input_file_1.txt"
        setFileContentAsSystemIn(file)
        // act
        val results = ConsoleInputProvider().getTestCases()
                .map { defaultPaintMixDecisionMaker.findBestBatch(it) }
        //assert
        Assertions.assertEquals(SuccessfulResult(listOf(MATTE, GLOSSY, GLOSSY, GLOSSY, GLOSSY)), results[0])
        Assertions.assertEquals(Impossible(), results[1])
        Assertions.assertEquals(1, logger.receivedEvents.filter { it is SuccessfulCombination }.size)
    }

    @Test
    fun `test impossible case`() {
        // arrange
        val file = "input_file_1.txt"
        setFileContentAsSystemIn(file)
        // act
        val results = ConsoleInputProvider().getTestCases()
                .map { defaultPaintMixDecisionMaker.findBestBatch(it) }
        //assert
        Assertions.assertEquals(SuccessfulResult(listOf(MATTE, GLOSSY, GLOSSY, GLOSSY, GLOSSY)), results[0])
        Assertions.assertEquals(Impossible(), results[1])
        Assertions.assertEquals(1, logger.receivedEvents.filter { it is SuccessfulCombination }.size)
    }

    @Test
    fun `all results are glossy`() {
        // arrange
        setFileContentAsSystemIn("allResultsAreGlossy.txt")
        // act
        val results = ConsoleInputProvider().getTestCases()
                .map { defaultPaintMixDecisionMaker.findBestBatch(it) }

        Assertions.assertEquals(SuccessfulResult(listOf(GLOSSY, GLOSSY, GLOSSY)), results[0])
    }

    @Test
    fun `test it with a single batch`() {
        // arrange
        setFileContentAsSystemIn("singleEntry.txt")
        // act
        val results = ConsoleInputProvider().getTestCases()
                .map { defaultPaintMixDecisionMaker.findBestBatch(it) }
        // assert
        Assertions.assertEquals(1, results.size)
        Assertions.assertEquals(SuccessfulResult(listOf(GLOSSY, MATTE, GLOSSY, GLOSSY, GLOSSY, GLOSSY, GLOSSY, MATTE)), results[0])
    }

    @Test
    fun `test it with a small batch`() {
        // arrange
        setFileContentAsSystemIn("smallbatch.txt")
        // act
        val results = ConsoleInputProvider().getTestCases()
                .map { defaultPaintMixDecisionMaker.findBestBatch(it) }
        // assert
        Assertions.assertEquals(100, results.size)
        Assertions.assertEquals(SuccessfulResult(listOf(GLOSSY, MATTE, GLOSSY, GLOSSY, GLOSSY, GLOSSY, GLOSSY, MATTE)), results[1])
        Assertions.assertEquals(SuccessfulResult(listOf(GLOSSY, GLOSSY, GLOSSY)), results[10])
    }

    @Test
    fun `test it with a big batch`() {
        // arrange
        setFileContentAsSystemIn("bigbatch.txt")
        // act
        ConsoleInputProvider().getTestCases()
                .map { defaultPaintMixDecisionMaker.findBestBatch(it) }
        //assert
        // assuming the logic is correct this is just checking there are no stackoverflow or oom errors
    }


}