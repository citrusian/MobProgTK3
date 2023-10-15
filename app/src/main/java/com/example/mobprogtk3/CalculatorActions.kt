package com.example.mobprogtk3

import android.content.Context

sealed class CalculatorActions {
    data class Number (val number: Int): CalculatorActions()
    object Clear: CalculatorActions()
    object Delete: CalculatorActions()
    object Decimal: CalculatorActions()
    object Calculate: CalculatorActions()
    object Home: CalculatorActions()
    object Report: CalculatorActions()
    data class Save(val context: Context) : CalculatorActions()
    data class Operation(val operation: CalculatorOperation): CalculatorActions()
}