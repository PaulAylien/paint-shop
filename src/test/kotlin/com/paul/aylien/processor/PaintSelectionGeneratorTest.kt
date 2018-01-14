package com.paul.aylien.processor

import com.paul.aylien.input.Color
import com.paul.aylien.input.Customer
import com.paul.aylien.input.Finish
import com.paul.aylien.input.Paint
import com.paul.aylien.processor.tree.Root
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PaintSelectionGeneratorTest {


    @Test
    fun `when choosing a paint to satisfy a users requirement glossy is given priority`() {
        val paintSelectionGenerator = LeafNodeBuilder()
        val generateSelection = paintSelectionGenerator.generateSelection(Customer(sortedSetOf(Paint(Color(1), Finish.GLOSSY),
                Paint(Color(2), Finish.MATTE))), Root()).toList()

        Assertions.assertEquals(generateSelection[0].getColorFinish(Color(1)), Finish.GLOSSY)
        Assertions.assertEquals(generateSelection[0].getColorFinish(Color(2)), null)

        Assertions.assertEquals(generateSelection[1].getColorFinish(Color(1)), null)
        Assertions.assertEquals(generateSelection[1].getColorFinish(Color(2)), Finish.MATTE)
    }

}