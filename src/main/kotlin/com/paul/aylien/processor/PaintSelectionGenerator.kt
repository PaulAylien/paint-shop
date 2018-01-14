package com.paul.aylien.processor

import com.paul.aylien.input.Customer
import com.paul.aylien.processor.tree.PaintPreferenceNode

class PaintSelectionGenerator {

    /**
     * Responsible for building tree branches which satisfy the given {@param customer}
     *
     * @param customer the customer to satisfy
     * @param currentPaintSelection the paints selected to satisfy already processed customers
     * @return a list of branches which satisfy the current customer
     */
    fun generateSelection(customer: Customer,
                          currentPaintSelection: PaintPreferenceNode): List<PaintPreferenceNode> =
            if (isCustomerAlreadySatisfiedByTree(customer, currentPaintSelection)) {
                listOf(currentPaintSelection)
            } else {
                customer.preferences
                        .filter { currentPaintSelection.isColorFree(it.color) }
                        .map { currentPaintSelection.newLeafNodePathWith(it) }
            }

    /**
     * An optimization as described in the readme
     *
     * https://github.com/PaulAylien/paint-shop#optimization-4-check-if-a-customer-is-satisfied-by-the-current-branch-before-adding-nodes-for-them
     */
    private fun isCustomerAlreadySatisfiedByTree(customer: Customer, currentPaintSelection: PaintPreferenceNode) =
            customer.preferences
                    .any { currentPaintSelection.getColorFinish(it.color) == it.finish }

}
