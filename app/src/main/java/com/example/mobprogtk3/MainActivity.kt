package com.example.mobprogtk3

import android.accounts.AuthenticatorDescription
import android.hardware.lights.Light
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.mobprogtk3.ui.theme.MobProgTK3Theme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobprogtk3.ui.theme.PurpleGrey40
import com.example.mobprogtk3.ui.theme.md_theme_light_onSurfaceVariant
import com.example.mobprogtk3.ui.theme.md_theme_light_surfaceTint


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

@Composable
fun Calculator(
    state: CalculatorState,
    modifier: Modifier = Modifier,
    buttonSpacing: Dp = 8.dp,
    onAction: (CalculatorActions) -> Unit
) {

    // Get and Set screen size for text output
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    // 0.2f fits perfectly on test
    val textHeightPercentage = 0.2f
    val textHeight = (screenHeight * 0.2f)


    Box(
        modifier = modifier
            .fillMaxSize()
            .fillMaxWidth()
        ,
//        contentAlignment = Alignment.BottomCenter,

        ){
        Column(
            modifier = Modifier
                .fillMaxSize()
            ,
            verticalArrangement = Arrangement.spacedBy(buttonSpacing, alignment = Alignment.Bottom)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    text = state.number1 + (state.operation?.symbols ?: "") + state.number2,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(textHeight)
                        .padding(vertical = 32.dp)
                    ,
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp,
                    color = Color.White,
                    maxLines = 1
                )
            }

            // Unused, for backup
            val horizontalScale = 1.0f // -
            val verticalScale = 1.0f   // |
            Column(
                modifier = Modifier
                    // Scale the whole Column
                    // works on smaller size device,
                    // but weird on bigger device
                    // .scale(horizontalScale, verticalScale)
                    .fillMaxWidth()
                ,
                verticalArrangement = Arrangement.spacedBy(buttonSpacing, alignment = Alignment.Bottom)
            ) {

                // First Row
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ){
                    CalculatorButton(
                        symbols = "AC",
                        modifier = Modifier
                            .background(PurpleGrey40)
                            // Scale Formula
                            // (target AR) x (this weight)
                            .aspectRatio(3.6f)
                            .weight(2f),
                        onClick = {
                            onAction(CalculatorActions.Clear)
                        }
                    )
                    CalculatorButton(
                        symbols = "DEL",
                        modifier = Modifier
                            .background(PurpleGrey40)
                            .aspectRatio(1.8f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorActions.Delete)
                        }
                    )
                    CalculatorButton(
                        symbols = "/",
                        modifier = Modifier
                            .background(md_theme_light_surfaceTint)
                            .aspectRatio(1.8f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorActions.Operation(CalculatorOperation.Divide))
                        }
                    )
                }

                // Second Row
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ){
                    CalculatorButton(
                        symbols = "7",
                        modifier = Modifier
                            .background(Color.DarkGray)
                            .aspectRatio(1.8f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorActions.Number(7))
                        }
                    )
                    CalculatorButton(
                        symbols = "8",
                        modifier = Modifier
                            .background(Color.DarkGray)
                            .aspectRatio(1.8f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorActions.Number(8))
                        }
                    )
                    CalculatorButton(
                        symbols = "9",
                        modifier = Modifier
                            .background(Color.DarkGray)
                            .aspectRatio(1.8f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorActions.Number(9))
                        }
                    )
                    CalculatorButton(
                        symbols = "*",
                        modifier = Modifier
                            .background(md_theme_light_surfaceTint)
                            .aspectRatio(1.8f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorActions.Operation(CalculatorOperation.Multiply))
                        }
                    )
                }

                // Third Row
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ){
                    CalculatorButton(
                        symbols = "4",
                        modifier = Modifier
                            .background(Color.DarkGray)
                            .aspectRatio(1.8f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorActions.Number(4))
                        }
                    )
                    CalculatorButton(
                        symbols = "5",
                        modifier = Modifier
                            .background(Color.DarkGray)
                            .aspectRatio(1.8f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorActions.Number(5))
                        }
                    )
                    CalculatorButton(
                        symbols = "6",
                        modifier = Modifier
                            .background(Color.DarkGray)
                            .aspectRatio(1.8f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorActions.Number(6))
                        }
                    )
                    CalculatorButton(
                        symbols = "-",
                        modifier = Modifier
                            .background(md_theme_light_surfaceTint)
                            .aspectRatio(1.8f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorActions.Operation(CalculatorOperation.Subtract))
                        }
                    )
                }

                // Fourth Row
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ){
                    CalculatorButton(
                        symbols = "1",
                        modifier = Modifier
                            .background(Color.DarkGray)
                            .aspectRatio(1.8f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorActions.Number(1))
                        }
                    )
                    CalculatorButton(
                        symbols = "2",
                        modifier = Modifier
                            .background(Color.DarkGray)
                            .aspectRatio(1.8f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorActions.Number(2))
                        }
                    )
                    CalculatorButton(
                        symbols = "3",
                        modifier = Modifier
                            .background(Color.DarkGray)
                            .aspectRatio(1.8f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorActions.Number(3))
                        }
                    )
                    CalculatorButton(
                        symbols = "+",
                        modifier = Modifier
                            .background(md_theme_light_surfaceTint)
                            .aspectRatio(1.8f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorActions.Operation(CalculatorOperation.Add))
                        }
                    )
                }

                // Fifth Row
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ){

                    CalculatorButton(
                        symbols = "0",
                        modifier = Modifier
                            .background(Color.DarkGray)
                            // Scale Formula
                            // (target AR) x (this weight)
                            .aspectRatio(3.6f)
                            .weight(2f)
                        ,
                        onClick = {
                            onAction(CalculatorActions.Number(0))
                        }
                    )
                    CalculatorButton(
                        symbols = ".",
                        modifier = Modifier
                            .background(Color.DarkGray)
                            .aspectRatio(1.8f)
                            .weight(1f)
                        ,
                        onClick = {
                            onAction(CalculatorActions.Decimal)
                        }
                    )
                    CalculatorButton(
                        symbols = "=",
                        modifier = Modifier
                            .background(md_theme_light_surfaceTint)
                            .aspectRatio(1.8f)
                            .weight(1f)
                        ,
                        onClick = {
                            onAction(CalculatorActions.Calculate)
                        }
                    )
                }

                // Sixth Row
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ){
                    CalculatorButton(
                        symbols = "Home",
                        modifier = Modifier
                            .background(Color.DarkGray)
                            .aspectRatio(2f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorActions.home)
                        }
                    )
                    CalculatorButton(
                        symbols = "Laporan",
                        modifier = Modifier
                            .background(Color.DarkGray)
                            .aspectRatio(2f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorActions.report)
                        }
                    )
                    CalculatorButton(
                        symbols = "Simpan",
                        modifier = Modifier
                            .background(Color.DarkGray)
                            .aspectRatio(2f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorActions.report)
                        }
                    )
                }
            }
        }
    }
}