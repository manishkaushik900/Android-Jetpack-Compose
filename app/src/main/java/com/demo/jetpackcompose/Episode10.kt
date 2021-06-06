package com.demo.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.demo.jetpackcompose.ui.theme.JetpackComposeTheme


/*Intrinsics: there are times when you need some information about your children before measuring them.
* Intrinsics lets you query children before they're actually measured.
*To a composable, you can ask for its intrinsicWidth or intrinsicHeight:
*(min|max)IntrinsicWidth: Given this height, what's the minimum/maximum width you can paint your content properly.
*(min|max)IntrinsicHeight: Given this width, what's the minimum/maximum height you can paint your content properly.

* Whenever you are creating your custom layout, you can modify how intrinsics are calculated with the
* (min|max)Intrinsic(Width|Height)MeasurePolicy parameters;
* however, the defaults should be enough most of the time.
* Also, you can modify intrinsics with modifiers overriding the Density.
* (min|max)Intrinsic(Width|Height)Of methods of the Modifier interface which also have a good default.*/

class Episode10 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                // A surface container using the 'background' color from the theme
               TwoTexts(text1 ="Hello" , text2 ="Manish" )
            }
        }
    }
}

@Composable
fun TwoTexts(modifier: Modifier = Modifier, text1: String, text2: String) {
    Row(modifier = modifier.height(IntrinsicSize.Min)) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .wrapContentWidth(Alignment.Start),
            text = text1
        )

        Divider(color = Color.Black, modifier = Modifier.fillMaxHeight().width(1.dp))
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
                .wrapContentWidth(Alignment.End),
            text = text2
        )
    }
}

@Preview
@Composable
fun TwoTextsPreview() {
    JetpackComposeTheme() {
        Surface {
            TwoTexts(text1 = "Hi", text2 = "there")
        }
    }
}