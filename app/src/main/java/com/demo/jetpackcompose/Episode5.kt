package com.demo.jetpackcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.demo.jetpackcompose.ui.theme.JetpackComposeTheme
import com.google.accompanist.glide.rememberGlidePainter
import kotlinx.coroutines.launch

/*Layouts in Jetpack Compose
* When building your own composables, you can use the Slots API pattern to make them more reusable.
* Compose comes with built-in Material Component composables that you can use to create your app.
* The most high-level composable is Scaffold.*/

/*Scaffold allows you to implement a UI with the basic Material Design layout structure.
*It provides slots for the most common top-level Material components such as TopAppBar, BottomAppBar, FloatingActionButton and Drawer. With Scaffold, you make sure these components will be positioned and work together correctly.*/
class Episode5 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                AppContainer5 {
                    LayoutsCodelab()
                }
            }
        }
    }
}

@Composable
fun AppContainer5(content: @Composable () -> Unit) {
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
        content()
    }
}


@Composable
fun LayoutsCodelab() {
//    Text(text = "Hi there!")
    ScrollingList()
}


@Composable
fun SimpleList() {
    // We save the scrolling position with this state that can also
    // be used to programmatically scroll the list
    val scrollState = rememberLazyListState()
    /*: LazyColumn in Jetpack Compose is the equivalent of RecyclerView in Android Views.*/
    LazyColumn(state = scrollState) {
        items(100) {
//           Text(text = "Item #$it")
            ImageListItem(index = it)
        }
    }
}

@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {

        Image(
            painter = rememberGlidePainter("https://picsum.photos/300/300"),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)

        )
        Spacer(Modifier.width(10.dp))
        Text("Items #$index", style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun ScrollingList() {
    val listSize = 100
    // We save the scrolling position with this state
    val scrollState = rememberLazyListState()
    // We save the coroutine scope where our animated scroll will be executed
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row {
            Button(onClick = {
                coroutineScope.launch {
                    // 0 is the first item index
                    scrollState.animateScrollToItem(0)
                }
            }) {
                Text("Scroll to the top")
            }

            Button(onClick = {
                coroutineScope.launch {
                    // listSize - 1 is the last index of the list
                    scrollState.animateScrollToItem(listSize - 1)
                }
            }) {
                Text("Scroll to the end")
            }
        }

        LazyColumn(state = scrollState) {
            items(listSize) {
                ImageListItem(it)
            }
        }
    }
}


@Preview
@Composable
fun LayoutsCodelabPreview() {
    JetpackComposeTheme {
        AppContainer {
            LayoutsCodelab()
        }
    }
}

