package com.example.mobprogtk3

sealed class CalculatorActions {
    data class Number (val number: Int): CalculatorActions()
    object Clear: CalculatorActions()
    object Delete: CalculatorActions()
    object Decimal: CalculatorActions()
    object Calculate: CalculatorActions()
    object home: CalculatorActions()
    object report: CalculatorActions()
    object save: CalculatorActions()
    data class Operation(val operation: CalculatorOperation): CalculatorActions()
}