package com.example.mobprogtk3

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

// only getter
//class CalculatorVisibilityViewModel : ViewModel() {
////    var isCalculatorVisible: Boolean = false
//
//}

// Test setter
class CalculatorVisibilityViewModel : ViewModel() {
    private val _isCalculatorVisible = mutableStateOf(false)
    val isCalculatorVisible: State<Boolean> = _isCalculatorVisible

    fun setCalculatorVisibility(isVisible: Boolean) {
        _isCalculatorVisible.value = isVisible
    }
}
