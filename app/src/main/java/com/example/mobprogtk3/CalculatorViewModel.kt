package com.example.mobprogtk3

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mobprogtk3.database.SQLiteHelper
import android.content.Context
import android.util.Log
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController

class CalculatorViewModel: ViewModel() {
    var state by mutableStateOf(CalculatorState())
        private set

    fun onAction(action: CalculatorActions){
        when(action){
            is CalculatorActions.Number -> enterNumber(action.number)
            is CalculatorActions.Decimal -> enterDecimal()
            is CalculatorActions.Clear -> state = CalculatorState()
            is CalculatorActions.Operation -> enterOperation(action.operation)
            is CalculatorActions.Calculate -> performCalculation()
            is CalculatorActions.Delete -> performDeletion()
            is CalculatorActions.Home -> state = CalculatorState()
            is CalculatorActions.Report -> showReport()
            is CalculatorActions.Save -> performSave(action.context)
        }
    }



    private fun showReport() {
//        navController.navigate("report_screen")
    }


    private fun performSave(context: Context) {
        Log.d("SAVE DB", "performSave Called")
        if (state.number1.isNotBlank() && state.operation == null) {
            val result = state.number1
            SQLiteHelper(context).dbSave(result)
            Log.d("SAVE DB", "SQLiteHelper Pass")
            state = state.copy(
                number1 = "",
                number2 = "",
                operation = null
            )
        }
    }


    private fun performDeletion() {
        when {
            state.number2.isNotBlank() -> state = state.copy(
                number2 = state.number2.dropLast(1)
            )
            state.operation != null  -> state = state.copy(
                operation = null
            )
            state.number1.isNotBlank() -> state = state.copy(
                number1 = state.number1.dropLast(1)
            )
        }
    }

    private fun performCalculation() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        if (number1 != null && number2 != null){
            val result = when(state.operation){
                is CalculatorOperation.Add -> number1 + number2
                is CalculatorOperation.Subtract -> number1 - number2
                is CalculatorOperation.Multiply -> number1 * number2
                is CalculatorOperation.Divide -> number1 / number2
                // Safety return if somehow number is null
                null -> return
            }

            val decimalFlag = if (result.rem(1.0) == 0.0) {
                result.toInt().toString()
            } else {
                result.toString()
            }

            // Set number1 as result, then clear the number2 and operation
            state = state.copy(
                number1 = decimalFlag.take(15),
                number2 = "",
                operation = null
            )
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if (state.number1.isNotBlank()){
            state = state.copy(operation = operation)
        }

    }

    private fun enterDecimal() {
        if(state.operation == null
            && !state.number1.contains(".")
            && state.number1.isNotBlank()
            ){
            state = state.copy(
                number1 = state.number1 + "."
            )
            return
        }

        if(!state.number2.contains(".")
            && state.number2.isNotBlank()
        ){
            state = state.copy(
                number2 = state.number2 + "."
            )
        }


    }

    private fun enterNumber(number: Int) {
        if(state.operation == null){
            if(state.number1.length >= MAX_NUM_LEN){
                return
            }
            state = state.copy(
                number1 = state.number1 + number
            )
            return
        }

        if(state.number2.length >= MAX_NUM_LEN){
            return
        }
        state = state.copy(
            number2 = state.number2 + number
        )
    }

    companion object {
        private const val MAX_NUM_LEN = 8
    }












}