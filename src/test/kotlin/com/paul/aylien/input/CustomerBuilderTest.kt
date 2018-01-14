package com.paul.aylien.input

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class CustomerBuilderTest {


    @Test
    fun createCustomerFromLine() {
        val createCustomer = CustomerBuilder().createCustomer("3 3 0 1 0 4 0")

        Assertions.assertEquals(3, createCustomer.preferences.size)
        assert(createCustomer.preferences.contains(Paint(Color(3), Finish.GLOSSY)))
        assert(createCustomer.preferences.contains(Paint(Color(1), Finish.GLOSSY)))
        assert(createCustomer.preferences.contains(Paint(Color(4), Finish.GLOSSY)))
    }
}