package com.demo.jetpackcompose

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bookmarks
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.demo.jetpackcompose.ui.theme.JetpackComposeTheme
import kotlinx.coroutines.launch

/*Layouts in Jetpack Compose
*Slot APIs are a pattern Compose introduces to bring in a layer of customization on top of composables,
*in this use case, the available Material Components composables.
* When building your own composables, you can use the Slots API pattern to make them more reusable.
* Compose comes with built-in Material Component composables that you can use to create your app.
* The most high-level composable is Scaffold.*/

/*Scaffold allows you to implement a UI with the basic Material Design layout structure.
*It provides slots for the most common top-level Material components such as TopAppBar, BottomAppBar, FloatingActionButton and Drawer. With Scaffold,
* you make sure these components will be positioned and work together correctly.*/
class Episode5 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                LayoutsCodelab5()
            }
        }
    }
}


/*All parameters in the Scaffold API are optional except the body content that is of type
 *@Composable() (InnerPadding) -> Unit: the lambda receives a padding as a parameter.
 *That's the padding that should be applied to the content root composable to constrain the items
 * appropriately on the screen.*/

/*Scaffold has a slot for a top AppBar with the topBar parameter of type
 @Composable() (() -> Unit)?, meaning we can fill the slot with any composable we want.
For example, if we just want it to contain a h3 style text*/

/*Compose comes with a TopAppBar composable that has slots for a title, navigation icon, and actions.*/
@Composable
fun LayoutsCodelab5() {
    val context = LocalContext.current
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val scaffldState = rememberScaffoldState()
    Scaffold(
        scaffoldState =scaffldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "Episode 5") },
                navigationIcon = {
                    IconButton(onClick = {
                        ShowToast(context);
                        scope.launch { scaffldState.drawerState.open()}
                    }) {
                        Icon(
                            Icons.Rounded.Menu,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { ShowToast(context) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_star_border_24),
                            contentDescription = null
                        )
                    }
                }
            )

        },
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.Yellow,
                elevation = 10.dp
            ) {
                Row {
                    IconButton(onClick = { ShowToast(context) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_star_border_24),
                            contentDescription = null
                        )
                    }

                    IconButton(onClick = { ShowToast(context) }) {
                        Icon(
                            Icons.Rounded.Bookmarks,
                            contentDescription = null
                        )
                    }
                }
            }
        },
        drawerContent = {
            ModalDrawer(drawerState = drawerState,
                drawerContent = {
                    Button(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 16.dp),
                        onClick = { scope.launch { drawerState.close() } },
                        content = { Text("Close Drawer") }
                    )
                },
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = if (drawerState.isClosed) ">>> Swipe >>>" else "<<< Swipe <<<")
                        Spacer(Modifier.height(20.dp))
                        Button(onClick = { scope.launch { drawerState.open() } }) {
                            Text("Click to open")
                        }
                    }
                })
        }

    ) { innerPadding ->
        BodyContent(
            Modifier
                .padding(innerPadding)
                .padding(8.dp)
        )
    }
}

@Composable fun DrawerBuild(){

}

fun ShowToast(context: Context) {

    Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show()
}

/*To make our code more reusable and testable, we should structure it into small chunks.
*For that, let's create another composable function with the content of our screen.*/
@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "Hi there!")
        Text(text = "Thanks for going through the Layouts codelab")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    JetpackComposeTheme {
        LayoutsCodelab5()
    }
}