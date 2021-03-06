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

/*Create a row image tutorial with clicks*/

/*Layouts in Jetpack Compose
* When building your own composables, you can use the Slots API pattern to make them more reusable.
* Compose comes with built-in Material Component composables that you can use to create your app.
* The most high-level composable is Scaffold.*/

/*Scaffold allows you to implement a UI with the basic Material Design layout structure.
*It provides slots for the most common top-level Material components such as TopAppBar, BottomAppBar, FloatingActionButton and Drawer. With Scaffold, you make sure these components will be positioned and work together correctly.*/
class Episode4 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                AppContainer {
                    PhotographerCard()
                }
            }
        }
    }
}

@Composable
fun AppContainer(content: @Composable () -> Unit) {
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
        content()
    }
}

@Preview
@Composable
fun LayoutsCodelabPreview3() {
    JetpackComposeTheme {
        AppContainer {
            PhotographerCard()
        }
    }
}

@Composable
fun PhotographerCard() {
    val context = LocalContext.current

    //Modifier sequence matter according to component
    //like in Row if we use click after padding then it will not work
    Row(modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .background(MaterialTheme.colors.surface)
        .clickable(onClick = {Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show()})
        .fillMaxWidth()
        .padding(16.dp)
        ) {

        Surface(
            modifier = Modifier.size(50.dp).padding(2.dp).align(Alignment.CenterVertically),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
            border = BorderStroke(2.dp,color = Color.Black),elevation = 10.dp
        ) {
            Image(
                painter = painterResource(id = R.drawable.header),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
        }
        Spacer(Modifier.width(10.dp))
        Column(modifier = Modifier.padding(10.dp)
            .align(Alignment.CenterVertically)) {
            Text("Manish Kaushik", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = "Few minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }

}

