package com.demo.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.demo.jetpackcompose.ui.theme.JetpackComposeTheme

/*Hello World Program*/
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            Greeting("Manish")
            PreviewGreeting()
        }
    }
}

@Composable
fun Greeting(name:String){
    Text("Hello $name!")
}

@Preview
@Composable
fun  PreviewGreeting(){
    Greeting(name = "World")
}