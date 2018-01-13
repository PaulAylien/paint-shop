package com.paul.aylien.processor

import com.paul.aylien.input.Customer
import com.paul.aylien.input.Finish
import com.paul.aylien.input.Paint


class PaintSelectionGenerator {


    fun generateSelection(customers: List<Customer>,
                          currentPaintSelection: PaintSelection): List<PaintSelection> =
            if (customers.first().preferences
                    .sortedWith(matteHasLowestPriority())
                    .any { currentPaintSelection.getColorFinish(it.color) == it.finish }) {
                listOf(currentPaintSelection)
            } else {
                customers.first().preferences
                        .sortedWith(matteHasLowestPriority())
                        .filter { currentPaintSelection.isColorFree(it.color) }
                        .map { currentPaintSelection.plusPaint(it) }
            }


    private fun matteHasLowestPriority(): java.util.Comparator<Paint> {
        return Comparator { a, b ->
            when (a.finish) {
                b.finish -> 0
                Finish.MATTE -> 1
                Finish.GLOSSY -> -1
            }
        }
    }


}
