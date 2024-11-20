package io.github.chethann.cmp.adaptive.ui.screen.adaptive

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.chethann.cmp.adaptive.ui.components.adaptiveBoxWithConstraint.AdaptiveDisplayDataOne
import io.github.chethann.cmp.adaptive.ui.components.adaptiveBoxWithConstraint.AdaptiveExampleOne
import kotlin.random.Random
import kotlin.uuid.ExperimentalUuidApi

@Composable
fun AdaptiveScreen() {
    val images = remember { getTestImageList() }

    LazyColumn {
        items(100) {
            SimpleCardWithImages(images.randomSublistWithRandomSize())
        }
    }
}

@OptIn(ExperimentalUuidApi::class)
@Composable
fun SimpleCardWithImages(images: List<ImageVector>) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
        ,
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Header Text
            Text(
                text = "Card Header",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Subheader Text
            Text(
                text = "Subheader Text",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Description
            Text(
                text = "This is a description of the card. It gives more details about the content of the card and can span multiple lines if necessary.",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            BoxWithConstraints {
                AdaptiveExampleOne(
                    AdaptiveDisplayDataOne(images = images)
                )
            }
        }
    }
}

fun <T> List<T>.randomSublistWithRandomSize(): List<T> {
    val randomSize = Random.nextInt(0, this.size + 1) // Random size between 0 and the size of the list
    return this.shuffled().take(randomSize)
}

fun getTestImageList(): List<ImageVector> {
    return listOf(Icons.Filled.Home,
        Icons.Filled.Add,
        Icons.Filled.Call,
        Icons.Filled.Done,
        Icons.Filled.Face,
        Icons.Filled.Warning,
        Icons.Filled.Info,
        Icons.Filled.KeyboardArrowUp,
        Icons.Filled.Lock,
        Icons.Filled.Menu,
        Icons.Filled.Notifications,
        Icons.Filled.Home,
        Icons.Filled.Lock,
        Icons.Filled.Menu,
        Icons.Filled.Notifications,
        Icons.Filled.Home,
    )
}