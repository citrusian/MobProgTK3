package com.example.mobprogtk3

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {
    var state by mutableStateOf(CalculatorState())
        private set

    fun onAction(action: CalculatorActions){
        when(action){
            is CalculatorActions.Number -> enterNumber(action.number)
            is CalculatorActions.Decimal -> enterDeciman()
            is CalculatorActions.Clear -> state = CalculatorState()
            is CalculatorActions.Operation -> enterOperation(action.operation)
            is CalculatorActions.Calculate -> performCalculation()
            is CalculatorActions.Delete -> performDeletion()
//            is CalculatorActions.home -> state = CalculatorState()
//            is CalculatorActions.report -> state = CalculatorState()
        }
    }

    private fun performDeletion() {
        TODO("Not yet implemented")
    }

    private fun performCalculation() {
        TODO("Not yet implemented")
    }

    private fun enterOperation(operation: CalculatorOperation) {

    }

    private fun enterDeciman() {
        TODO("Not yet implemented")
    }

    private fun enterNumber(number: Int) {

    }
}