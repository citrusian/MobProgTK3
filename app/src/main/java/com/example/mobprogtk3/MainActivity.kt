package com.example.mobprogtk3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobprogtk3.ui.theme.MobProgTK3Theme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


// Preview, call MainContent function
@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    MainContent()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobProgTK3Theme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                    ,
                    color = MaterialTheme.colorScheme.background
                )
                {
                    // Combine Preview and Real Content
                    MainContent()
                }
            }
        }
    }
}

@Composable
fun MainContent() {
    val viewModel = viewModel<CalculatorViewModel>()
    val state = viewModel.state
    val buttonSpacing = 8.dp

    Calculator(
        state = state,
        onAction = viewModel::onAction,
        buttonSpacing = buttonSpacing,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(16.dp)
    )
}