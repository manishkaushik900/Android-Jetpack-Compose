package com.demo.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.demo.jetpackcompose.ui.theme.JetpackComposeTheme

/*Constraint Layout using Compose
* References are created using createRefs() (or createRef())
* and each composable in ConstraintLayout needs to have a reference associated.
* Constraints are provided using the constrainAs modifier which takes the reference as a parameter and lets you specify its constraints in the body lambda.
* Constraints are specified using linkTo or other helpful methods
* parent is an existing reference that can be used to specify constraints towards the ConstraintLayout composable itself.
* */
class Episode9 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                LargeConstraintLayout()
            }
        }
    }
}

@Composable
private fun LargeConstraintLayout() {
    ConstraintLayout {

        //craete reference for constarints
        val (button, button2, text) = createRefs()

        // Assign reference "button" to the Button composable
        // and constrain it to the top of the ConstraintLayout
        Button(onClick = {}, Modifier.constrainAs(button) {
            top.linkTo(parent.top, margin = 16.dp)

        }) {
            Text(text = "Button 1")
        }


        //Customizing dimensions
        val guideline = createGuidelineFromStart(fraction = 0.5f)

        // Assign reference "text" to the Text composable
        // and constrain it to the bottom of the Button composable
        Text(text = "Hello Manish!", Modifier.constrainAs(text) {
            top.linkTo(button.bottom, margin = 16.dp)
//            centerAround(button.end)

            //for customizing dimension
            linkTo(start = guideline, end = parent.end)



            /*Available Dimension behaviors are:

preferredWrapContent - the layout is wrap content, subject to the constraints in that dimension.
wrapContent - the layout is wrap content even if the constraints would not allow it.
fillToConstraints - the layout will expand to fill the space defined by its constraints in that dimension.
preferredValue - the layout is a fixed dp value, subject to the constraints in that dimension.
value - the layout is a fixed dp value, regardless of the constraints in that dimension
*/
            width = Dimension.preferredWrapContent
            //or
            //width = Dimension.preferredWrapContent.atLeast(100.dp)
        })

        val enbarrier = createEndBarrier(button, text)

        /*linkTo can be used to constrain with guidelines and barriers the same way it works for edges of layouts.
        * */
        Button(onClick = {}, Modifier.constrainAs(button2) {
            top.linkTo(parent.top, margin = 16.dp)
            start.linkTo(enbarrier)

        }) {
            Text(text = "Button 2")
        }

    }
}

@Composable
fun DecoupledConstraintLayout() {
    BoxWithConstraints {
        val constraints = if (maxWidth < maxHeight) {
            decoupledConstraints(margin = 16.dp) // Portrait constraints
        } else {
            decoupledConstraints(margin = 32.dp) // Landscape constraints
        }

        ConstraintLayout(constraints) {
            Button(
                onClick = { /* Do something */ },
                modifier = Modifier.layoutId("button")
            ) {
                Text("Button")
            }

            Text("Text", Modifier.layoutId("text"))
        }
    }
}

private fun decoupledConstraints(margin: Dp): androidx.constraintlayout.compose.ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")

        constrain(button) {
            top.linkTo(parent.top, margin = margin)
        }
        constrain(text) {
            top.linkTo(button.bottom, margin)
        }
    }
}

@Composable
fun DefaultPreview91() {
    JetpackComposeTheme {
        DecoupledConstraintLayout()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview9() {
    JetpackComposeTheme {
        LargeConstraintLayout()
    }
}