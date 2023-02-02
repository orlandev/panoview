package com.orlandev.panoview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.orlandev.panoview.composable.PanoView
import com.orlandev.panoview.ui.theme.PanoviewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PanoviewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    PanoViewTest()
                }
            }
        }
    }
}

@Composable
fun PanoViewTest() {

    val listOfPano = listOf<String>(
        "https://ik.imagekit.io/6xgh00mrhaz/youtube_video/pano1_wAO9Dt8BH.jpg",
        "https://ik.imagekit.io/6xgh00mrhaz/youtube_video/pano2_yxr08j5O_.jpg",
        "https://ik.imagekit.io/6xgh00mrhaz/youtube_video/cemetery360_AsO1thfnL"
    )

    LazyColumn(modifier = Modifier.fillMaxSize()) {

        items(listOfPano) {
            PanoView(modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(8.dp), url = it)
        }
    }
}