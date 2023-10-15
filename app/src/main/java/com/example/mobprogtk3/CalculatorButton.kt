package com.example.mobprogtk3

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity

@Composable
fun CalculatorButton(
    symbols: String,
    modifier: Modifier,
    onClick: () -> Unit
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val fontSize = with(LocalDensity.current) { (screenHeight / 12).toSp() }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(CircleShape)
            .clickable { onClick() }
            .then(modifier)
    ){
        Text(
            text = symbols,
            fontSize = fontSize,
            color = Color.White,
        )
    }
}