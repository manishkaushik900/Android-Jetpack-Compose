package com.demo.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.demo.jetpackcompose.ui.theme.JetpackComposeTheme

/*Flexible Layout*/
class Episode3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme{


                MyAppNew {
                    MyScreenContentNew()
                }
            }
        }
    }
}

@Composable
fun MyAppNew(content: @Composable () -> Unit) {
    Surface(color = Color.Yellow) {
        content()
    }
}

@Composable
fun MyScreenContentNew(names: List<String> = List(100) { "Android $it" }) {
    val counterState = remember {
        mutableStateOf(0)
    }

    Column(modifier = Modifier.fillMaxHeight()) {
        /*Column(modifier = Modifier.weight(1f)) {

            for (name in names) {
                Greeting2(name = name)
                Divider(color = Color.Black)
            }
        }*/

        NamesList(modifier = Modifier.weight(1f), names)

        Counter2(counterState.value, updateCounter = { newCount ->
            counterState.value = newCount

        })
    }


}


@Composable
fun Counter2(count: Int, updateCounter: (Int) -> Unit) {

    Button(
        onClick = { updateCounter(count + 1) }, colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count > 5) Color.Green else Color.White
        )
    ) {
        Text("Clicked $count")
    }

}

/*To display a scrollable column we use a LazyColumn.
*LazyColumn renders only the visible items on screen, allowing performance gains when rendering a big list.
*It is equivalent to RecyclerView in Android Views.
* LazyColumn doesn't recycle its children like RecyclerView.
* It emits new Composables as you scroll through it and is still performant as emitting Composables is relatively cheap compared to instantiating Android Views.*/
@Composable
fun NamesList(
    modifier: Modifier = Modifier,
    names: List<String>
) {
    LazyColumn(modifier = modifier) {
        items(items = names) { name ->
            Greeting2(name = name)
            Divider(color = Color.Black)

        }
//        for (name in names) {
//            Greeting2(name = name)
//            Divider(color = Color.Black)
//        }
    }
}

@Composable
fun Greeting2(name: String) {
    var isSelected by remember {
        mutableStateOf(false)
    }

    val backgroundColor by animateColorAsState(if (isSelected) Color.Red else Color.Transparent)

    Text(
        text = "Hello $name!",
        modifier = Modifier
            .padding(24.dp)
            .background(color = backgroundColor)
            .clickable(onClick = { isSelected = !isSelected }),
        style = MaterialTheme.typography.h4
    )
}

@Preview("My Screen Preview")
@Composable
fun DefaultPreview2() {
    MyAppNew {
        MyScreenContentNew()
    }
}