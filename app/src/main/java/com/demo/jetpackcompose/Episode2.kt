package com.demo.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/*Compose State + Compose & Kotlin + Default Preview + Conatiner + Material Design*/
class Episode2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            DefaultPreview()
            MyApp {
//                NewsStory(name = "Manish")
                MyScreenContent()
            }

        }
    }
}

/*Later Step Basic
* What if you want to create a container that has all the common configurations of your app?
* To make a generic container, create a Composable function that takes as a parameter a Composable function (here called content)
*  which returns Unit.
* You return Unit because, as you might have noticed, Composable functions don't return UI components, they emit them.
* That's why they must return Unit :*/
@Composable
fun MyApp(content: @Composable () -> Unit) {
    /*If you want to set a different background color for the DefaultPreview,
    * you need to define a Surface that contains it:
    * The components nested inside Surface will be drawn on top of that background color
    * (unless specified otherwise by another Surface).*/

    Surface(color = Color.White) {
//        NewsStory("Android")
        //now change to content
        content()
    }
}

@Composable
fun NewsStory(name: String) {
    /*Compose makes it easy to take advantage of Material Design principles.
     *Apply MaterialTheme to the components you've created.*/
    MaterialTheme {
        val typography = MaterialTheme.typography

        /*The Column function lets you stack elements vertically.
        * By passing parameters to the Column call,
        * you can configure the column's size and position,
        * and how the column's children are arranged.*/

        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(R.drawable.header),
                contentDescription = null,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    /*One of the pillars of the Material Design System is Shape.
                    * Use the clip() function to round the corners of the image.
                    * The Shape is invisible, but the graphic is cropped to fit the Shape,
                    * so it now has slightly rounded corners.*/
                    .clip(shape = RoundedCornerShape(10.dp)),

                contentScale = ContentScale.Crop
            )

            /*Add a Spacer to separate the graphic from the headings.*/
            Spacer(modifier = Modifier.height(16.dp))

            Surface(color = Color.Yellow) {
                Text(
                    text = "Hello $name! Welcome to new world of Android, which is changing drastaically, so what to do we have to learn to grab a job",
                    style = typography.h6,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                "Davenport, California",
                style = typography.body2
            )

            Text("December 2018", style = typography.body1)
        }

    }
}

/* Compose State: If your data changes, you recall these functions with the new data, creating an updated UI.
* Compose offers tools for observing changes in your app's data, which will automatically recall your functions—this is called recomposing.
* Compose also looks at what data is needed by an individual composable so that it only needs to recompose components whose data has changed and can skip composing those that are not affected.
* Under the hood, Compose uses a custom Kotlin compiler plugin so when the underlying data changes, the composable functions can be re-invoked to update the UI hierarchy.
*
* To add internal state to a composable, use the mutableStateOf function, which gives a composable mutable memory.
* To not have a different state for every recomposition, remember the mutable state using remember.
* And, if there are multiple instances of the composable at different places on the screen, each copy will get its own version of the state.
*  You can think of internal state as a private variable in a class.
* The composable function will automatically be subscribed to it.
*  If the state changes, composables that read these fields will be recomposed.*/
@Composable
fun Counter() {

    val count = remember { mutableStateOf(0) }

    Button(onClick = { count.value++ }
    ,modifier = Modifier.padding(16.dp)
            .fillMaxWidth()) {
        Text("I've been clicked ${count.value} times")
    }
}

/*Calling Composable functions multiple times using Layouts
* You extract UI components into Composable functions so that you can reuse them without duplicating code.*/
/*@Composable
fun MyScreenContent() {
    Column {
        NewsStory("Android")
//  Divider is a provided composable function that creates a horizontal divider.
        Divider(color = Color.Black)
        NewsStory("there")
    }
}*/

/*Second Way
* Compose and Kotlin
* Compose functions can be called like any other function in Kotlin. This makes
* building UIs really powerful since you can add statements to influence how the UI will be displayed.*/
@Composable
fun MyScreenContent(names: List<String> = listOf("Android"/*, "Manish"*/)) {
    Column {
        for (name in names) {
            NewsStory(name = name)
            Divider(color = Color.Black)
        }
        Divider(color = Color.Transparent, thickness = 32.dp)
        Counter()
    }
}

/*It's a best practice to create separate preview functions
  that aren't called by the app;
  having dedicated preview functions improves performance,
  and also makes it easier to set up multiple previews later on.
  So, create a default preview function that does nothing
  but call the MyApp() function*/
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent()
    }

}