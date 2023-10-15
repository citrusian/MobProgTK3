package com.example.mobprogtk3

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CalculatorVisibilityViewModel : ViewModel() {
    private val _isCalculatorVisible = mutableStateOf(true)
    val isCalculatorVisible: State<Boolean> = _isCalculatorVisible

    fun setCalculatorVisibility(isVisible: Boolean) {
        _isCalculatorVisible.value = isVisible
    }
}
