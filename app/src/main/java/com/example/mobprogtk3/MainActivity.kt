package com.example.mobprogtk3

import android.accounts.AuthenticatorDescription
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.mobprogtk3.ui.theme.MobProgTK3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobProgTK3Theme {
                Surface(
                    modifier = Modifier
//                        .fillMaxSize()
                    // Jetpack Compose placing verticalScroll here
                    // https://developer.android.com/jetpack/compose/touch-input/pointer-input/scroll
//                        .verticalScroll(rememberScrollState())
                    ,
                    color = MaterialTheme.colorScheme.background
                )
                {
                    val imageUrls = listOf(
                        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75",
                        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2F2d4b8fde-5ec2-4c72-b804-29d3cc14e3d7.799a99c1196c2fd4.gif&w=1920&q=75",
                        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75",
                        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75"
                    )
                    val imageDesc = listOf(
                        "Google Doodle Desc ",
                        "Google Doodle Desc ",
                        "Google Doodle Desc ",
                        "Google Doodle Desc ",
                    )
                    val imageTitles = listOf(
                        "Google Doodle ",
                        "Google Doodle ",
                        "Google Doodle ",
                        "Google Doodle ",
                    )

                    LazyColumn (
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(10.dp)
                    ) {
                        items(imageUrls.size) { index ->
                            ImageCard(
                                painter = rememberImagePainter(imageUrls[index]),
                                contentDescription = "Google Doodle ${index + 1}",
                                title = "Google Doodle ${index + 1}"
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }

//                ________________________
                }
            }
        }
    }
}


@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        // Tutorial use old command, new one in
        // https://developer.android.com/jetpack/compose/components/card#elevated
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
    ){
        Box(
            modifier = Modifier
                .height(200.dp)
        ){
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
            )
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        // Hardcoded Gradient Size
                        startY = 400f

                    )
                )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
                ,
                contentAlignment = Alignment.BottomStart
            ){
                Text(title, style = TextStyle(color = Color.White, fontSize = 16.sp))
            }
        }
    }
}

// LazyColumn, eg: RecycleView
@Preview(showBackground = true)
@Composable
fun ImageCardPreview() {

    val imageUrls = listOf(
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75",
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2F2d4b8fde-5ec2-4c72-b804-29d3cc14e3d7.799a99c1196c2fd4.gif&w=1920&q=75",
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75",
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75"
    )

    val imageDesc = listOf(
        "Google Doodle Desc ",
        "Google Doodle Desc ",
        "Google Doodle Desc ",
        "Google Doodle Desc ",
    )

    val imageTitles = listOf(
        "Google Doodle ",
        "Google Doodle ",
        "Google Doodle ",
        "Google Doodle ",
    )

    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(imageUrls.size) { index ->
            ImageCard(
                painter = rememberImagePainter(imageUrls[index]),
                contentDescription = "${imageDesc[index]} ${index + 1}",
                // Test using index to call desc, and call index number +1
                title = "Google Doodle ${index + 1}"
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}




// Using Simple Column
//
//@Preview(showBackground = true)
//@Composable
//fun ImageCardPreview() {
//    val modifier = Modifier
//        .padding(16.dp)
//         https://developer.android.com/jetpack/compose/touch-input/pointer-input/scroll
//        .verticalScroll(rememberScrollState())
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(10.dp)
//    ) {
//        ImageCard(
//            painter = rememberImagePainter("https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75"),
//            contentDescription = "Google Doodle 1",
//            title = "Google Doodle 1"
//        )
//        Spacer(modifier = Modifier.height(10.dp))
//        ImageCard(
//            painter = rememberImagePainter("https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2F2d4b8fde-5ec2-4c72-b804-29d3cc14e3d7.799a99c1196c2fd4.gif&w=1920&q=75"),
//            contentDescription = "Google Doodle 2",
//            title = "Google Doodle 2"
//        )
//        Spacer(modifier = Modifier.height(10.dp))
//        ImageCard(
//            painter = rememberImagePainter("https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75"),
//            contentDescription = "Google Doodle 3",
//            title = "Google Doodle 3"
//        )
//        Spacer(modifier = Modifier.height(10.dp))
//        ImageCard(
//            painter = rememberImagePainter("https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75"),
//            contentDescription = "Google Doodle 4",
//            title = "Google Doodle 4"
//        )
//        Spacer(modifier = Modifier.height(10.dp))
//        ImageCard(
//            painter = rememberImagePainter("https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75"),
//            contentDescription = "Google Doodle 5",
//            title = "Google Doodle 5"
//        )
//    }
//}

