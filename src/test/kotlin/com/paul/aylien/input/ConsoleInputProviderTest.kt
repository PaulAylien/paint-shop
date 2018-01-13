package com.paul.aylien.input

import com.paul.aylien.TestUtils.setFileContentAsSystemIn
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

internal class ConsoleInputProviderTest {


    @Test
    fun `throws useful exception for invalid numberOfTestsCases`() {
        setFileContentAsSystemIn("invalid/numberOfTestCasesNotCorrectlySpecified")

        val exception = assertThrows(Exception::class.java,
                { ConsoleInputProvider().getTestCases() })
        assertEquals("error reading numberOfTestCases", exception.message)
    }

    @Test
    fun `throws useful exception for invalid numberOfPaints`() {
        setFileContentAsSystemIn("invalid/numberOfPaintsNotCorrectlySpecified")

        val exception = assertThrows(Exception::class.java,
                { ConsoleInputProvider().getTestCases() })

        assertEquals("error reading numberOfPaints", exception.message)
    }

    @Test
    fun `throws useful exception for invalid numberOfCustomers`() {
        setFileContentAsSystemIn("invalid/numberOfCustomersNotCorrectlySpecified")

        val exception = assertThrows(Exception::class.java,
                { ConsoleInputProvider().getTestCases() })
        assertEquals("error reading numberOfCustomers", exception.message)
    }
}