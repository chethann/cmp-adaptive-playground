package io.github.chethann.cmp.adaptive.ui.screen.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import kotlin.random.Random
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Composable
fun FeedScreen() {

    val testItems = remember {  (0..25).map { TestItemData(Uuid.random().toString()) } }
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

    LazyVerticalGrid(
        //columns = GridCells.Adaptive(minSize = 128.dp)
        columns = rememberColumns(windowSizeClass)
    ) {
        items(testItems) { item ->
            RandomColorBox()
        }
    }
}

@Composable
fun RandomColorBox() {
    // Generate a random color
    val randomColor = Color(
        red = Random.nextFloat(),
        green = Random.nextFloat(),
        blue = Random.nextFloat(),
        alpha = 1f
    )

    // Create a Box with random background color
    Box(
        modifier = Modifier
            .size(200.dp) // Adjust the size as needed
            .background(randomColor)
    )
}

data class TestItemData(
    val id: String
)

// A better way of determining number of columns
@Composable
fun rememberColumns(windowSizeClass: WindowSizeClass) = remember(windowSizeClass) {
    when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> GridCells.Fixed(1)
        WindowWidthSizeClass.MEDIUM -> GridCells.Fixed(2)
        else -> GridCells.Adaptive(250.dp)
    }
}
