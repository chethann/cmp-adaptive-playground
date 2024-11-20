package io.github.chethann.cmp.adaptive.ui.components.adaptiveBoxWithConstraint

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// This is little expensive as composition is deferred till layout phase. As layout phase data is need to determine what to render
@Composable
fun BoxWithConstraintsScope.AdaptiveExampleOne(data: AdaptiveDisplayDataOne) {
    val imageSize = 32.dp
    val padding = 12.dp

    val numberOfImagesThatCanBeShown = maxWidth.div(imageSize + padding).toInt().minus(1)
    val remainingImages = data.images.size - numberOfImagesThatCanBeShown
    val itemsToRender = remember(numberOfImagesThatCanBeShown) { data.images.take(numberOfImagesThatCanBeShown) }

    AnimatedContent(
        targetState = itemsToRender,
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(padding)) {
            itemsToRender.forEach {
                Icon(
                    contentDescription = "icon",
                    modifier = Modifier.size(imageSize),
                    imageVector = it
                )
            }

            if (remainingImages > 0) {
                Box(
                    modifier = Modifier
                        .size(imageSize)
                        .clip(RoundedCornerShape(size = imageSize))
                        .background(MaterialTheme.colorScheme.secondary)
                ) {
                    Text(
                        "+ $remainingImages",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }

}

data class AdaptiveDisplayDataOne(
    val images: List<ImageVector>
)