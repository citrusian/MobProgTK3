package com.example.mobprogtk3

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobprogtk3.ui.theme.MobProgTK3Theme


// Preview, call MainContent function
@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    var calculatorVisibilityViewModel = viewModel<CalculatorVisibilityViewModel>()
    MainContent(calculatorVisibilityViewModel)
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
                    // test using viewmodel for visibility val
                    var calculatorVisibilityViewModel = viewModel<CalculatorVisibilityViewModel>()

                    // Combine Preview and Real Content
                    MainContent(calculatorVisibilityViewModel)
                }
            }
        }
    }
}

@Composable
fun MainContent(calculatorVisibilityViewModel: CalculatorVisibilityViewModel) {
    val viewModel = viewModel<CalculatorViewModel>()
    val state = viewModel.state
    val buttonSpacing = 8.dp
//    var isCalculatorVisible by remember { mutableStateOf(calculatorVisibilityViewModel.isCalculatorVisible) }

    if (calculatorVisibilityViewModel.isCalculatorVisible.value) {
        Calculator(
            state = state,
            onAction = viewModel::onAction,
            buttonSpacing = buttonSpacing,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
                .padding(16.dp),
            calculatorVisibilityViewModel = calculatorVisibilityViewModel
        )
    } else {
        SavedValue(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
                .padding(16.dp),
            isCalculatorVisible = calculatorVisibilityViewModel.isCalculatorVisible.value,
            onHomeClick = {
                calculatorVisibilityViewModel.setCalculatorVisibility(true)
            },
            onLaporanClick = {
                // Do nothing
            }
        )
    }
}

@Composable
fun SavedValue(
    modifier: Modifier = Modifier,
    isCalculatorVisible: Boolean,
    onHomeClick: () -> Unit,
    onLaporanClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    text = "This is the report screen",
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                    ,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = Color.White,
                )

                // Sixth Row
                Row (
                    modifier = Modifier.fillMaxHeight(),
                    verticalAlignment = Alignment.Bottom

                ){
                    CalculatorButton(
                        symbols = "Home",
                        modifier = Modifier
                            .background(Color.DarkGray)
                            .aspectRatio(3.5f)
                            .weight(1f),
                        onClick = onHomeClick // Call the lambda for "Home" click
                    )
                    CalculatorButton(
                        symbols = "Laporan",
                        modifier = Modifier
                            .background(Color.DarkGray)
                            .aspectRatio(3.5f)
                            .weight(1f),
                        onClick = onLaporanClick // Call the lambda for "Laporan" click
                    )
                }
            }
        }
    }
}
