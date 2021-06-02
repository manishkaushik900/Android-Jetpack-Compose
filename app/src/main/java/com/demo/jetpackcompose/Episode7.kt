package com.demo.jetpackcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.demo.jetpackcompose.ui.theme.JetpackComposeTheme

/*Custom Layouts
* Use the layout modifier to manually control how to measure and position an element.
* Compose UI does not permit multi-pass measurement.
* This means that a layout element may not measure any of its children more than once in order to try different measurement configurations
* When using the layout modifier, you get two lambda parameters:
* measurable: child to be measured and placed
* constraints: minimum and maximum for the width and height of the child
*
*  If an element has child elements it may measure each of them to help determine its own size.
*  Once an element reports its own size, it has an opportunity to place its child elements relative to itself.
*
* Top — The maximum distance above the baseline for the tallest glyph in the font at a given text size.
* Bottom — The maximum distance below the baseline for the lowest glyph in the font at a given text size.
* Ascent — The recommended distance above the baseline for singled spaced text.
* Leading — The recommended additional space to add between lines of text.
* Dscent - The recommended distance below the baseline for singled spaced text.
* Baseline is what the first four are measured from.
* It is the line which forms the base that the text sits on,
* even though some characters (like g, y, j, etc.) might have parts that go below the line.
* It is comparable to the lines you write on in a lined notebook.
*https://suragch.medium.com/meaning-of-top-ascent-baseline-descent-bottom-and-leading-in-androids-fontmetrics-c80cf019aef5
* Remember that when drawing on a canvas in Java and Android, going down is an increase in y and going up is a decrease in y.
* That means that FontMetrics’ top and ascent are negative numbers since they are measured from the baseline
* (while descent and bottom are positive numbers).
* Thus, to get the distance from top to bottom you would need to do bottom - top.
* The leading is the distance between the bottom of one line and the top of the next line.
*
**/

class Episode7 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                Text("Hi there!", Modifier.firstBaselineToTop(32.dp))

            }
        }
    }
}


/* Use the layout modifier
*The result of a measure() call on a Measurable is a Placeable that can be positioned by calling placeRelative(x, y)
* you can position the composable on the screen by calling placeable.placeRelative(x, y).
* If you don't call placeRelative, the composable won't be visible.
* placeRelative automatically adjusts the position of the placeable based on the current layoutDirection.
*
* In this case, the y position of the text corresponds to the top padding minus the position of the first baseline
* */
fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = this.then(
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        // Check the composable has a first baseline
        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
        val firstBaseline = placeable[FirstBaseline]

        // Height of the composable with padding - first baseline
        val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
        val height = placeable.height + placeableY
        layout(placeable.width, height) {
            // Where the composable gets placed
            placeable.placeRelative(0, placeableY)
        }

    }
)


/*Using the Layout composable
* The minimum required parameters for a CustomLayout are a modifier and content;
* these parameters are then passed to Layout.
* In the trailing lambda of Layout (of type MeasurePolicy),
* you get the same lambda parameters as you get with the layout modifier.
* As before, the first thing to do is measure our children that can only be measured once. Similarly to how the layout modifier works,
*
* Specify the size of our own Column by calling the layout(width, height)
* */
@Composable
fun MyOwnColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        // Don't constrain child views further, measure them with given constraints
        // List of measured children
        val placeables = measurables.map { measurable ->
            // Measure each child
            measurable.measure(constraints)
        }

        // Track the y co-ord we have placed children up to
        var yPosition = 0

        // Set the size of the layout as big as it can
        layout(constraints.maxWidth, constraints.maxHeight) {
            // Place children in the parent layout
            placeables.forEach { placeable ->
                // Position item on the screen
                placeable.placeRelative(x = 0, y = yPosition)

                // Record the y co-ord placed up to
                yPosition += placeable.height
            }
        }
    }
}

@Preview
@Composable
fun BodyContents(modifier: Modifier = Modifier) {
    MyOwnColumn(modifier.padding(8.dp)) {
        Text("MyOwnColumn")
        Text("places items")
        Text("vertically.")
        Text("We've done it by hand!")
    }
}

@Preview
@Composable
fun TextWithPaddingToBaselinePreview() {
    JetpackComposeTheme {
        Text("Hi there!", Modifier.firstBaselineToTop(32.dp))
    }
}

@Preview
@Composable
fun TextWithNormalPaddingPreview() {
    JetpackComposeTheme {
        Text("Hi there!", Modifier.padding(top = 32.dp))
    }
}
