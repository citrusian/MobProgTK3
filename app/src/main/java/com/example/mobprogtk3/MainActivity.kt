package com.example.mobprogtk3

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobprogtk3.database.SQLiteHelper
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
    val context = LocalContext.current
    val viewModel = viewModel<CalculatorViewModel>()
    val sqliteHelper = remember { SQLiteHelper(context) }
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
            onHomeClick = {
                calculatorVisibilityViewModel.setCalculatorVisibility(true)
            },
            onLaporanClick = {
                // Do nothing
            },
            sqliteHelper = sqliteHelper
        )
    }
}



@Composable
fun SavedValue(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit,
    onLaporanClick: () -> Unit,
    sqliteHelper: SQLiteHelper
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
                    text = "Laporan",
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                    ,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = Color.White,
                )

                // Display Last value (10, can be changed)
                SavedValues(sqliteHelper)

                // Navigation
                Row (
                    modifier = Modifier.fillMaxHeight(),
                    verticalAlignment = Alignment.Bottom,
                    // Hardcoded 8dp, following the rest
                    horizontalArrangement = Arrangement.spacedBy(8.dp)

                ){
                    CalculatorButton(
                        symbols = "Home",
                        modifier = Modifier
                            .background(Color.DarkGray)
                            .aspectRatio(4.0f)
                            .weight(2f),
                        onClick = onHomeClick
                    )
                    CalculatorButton(
                        symbols = "Laporan",
                        modifier = Modifier
                            .background(Color.DarkGray)
                            .aspectRatio(4.0f)
                            .weight(2f),
                        onClick = onLaporanClick
                    )
                }
            }
        }
    }
}

// Create a composable function to display the last 10 values
@SuppressLint("Range")
@Composable
fun SavedValues(sqliteHelper: SQLiteHelper) {
    val values = remember { mutableStateListOf<String>() }

    LaunchedEffect(Unit) {
        val db = sqliteHelper.readableDatabase
        val cursor = db.query(
            "result",
            arrayOf("value"),
            null,
            null,
            null,
            null,
            "_id DESC",
            // Hard Limit
            "10"
        )
        while (cursor.moveToNext()) {
            val value = cursor.getString(cursor.getColumnIndex("value"))
            values.add(value)
        }

        cursor.close()
        db.close()
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "10 Angka Terakhir:",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier.padding(10.dp)
        )
        for (value in values) {
            Text(
                text = value,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}

