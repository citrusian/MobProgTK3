package com.example.mobprogtk3

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.ViewModel

class ReportVView {
    @Composable
    fun ReportScreen() {
        Text(text = "This is the report screen", modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
    }

    class CalculatorViewModel : ViewModel() {

        fun onAction(action: CalculatorActions) {
            when (action) {

                is CalculatorActions.Report -> showReport()
                else -> {
                    return
                }
            }
        }

        private fun showReport() {

        }
    }

}