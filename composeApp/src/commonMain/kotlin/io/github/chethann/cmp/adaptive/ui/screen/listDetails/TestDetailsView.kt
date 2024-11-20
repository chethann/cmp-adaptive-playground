package io.github.chethann.cmp.adaptive.ui.screen.listDetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldDestinationItem
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldValue
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun DetailsViewWithRandomTexts(itemEntity: ItemEntity, navigator: ThreePaneScaffoldNavigator<ItemEntity>) {
    val textPool = listOf(
        "This is a high-level overview of the topic.",
        "Details go here with examples and elaborations.",
        "Additional information supports the main discussion.",
        "This is placeholder text for testing.",
        "Random facts and insights might appear here.",
        "Use this space to summarize key points."
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        if (navigator.scaffoldValue[ListDetailPaneScaffoldRole.Detail] == PaneAdaptedValue.Expanded ) {
            Text("Go back", modifier = Modifier.clickable {
                navigator.navigateTo(ListDetailPaneScaffoldRole.List, null)
            })

            Text("Go to extra pane", modifier = Modifier.clickable {
                navigator.navigateTo(ListDetailPaneScaffoldRole.Extra, itemEntity)
            })
        }

        Text(itemEntity.id)

        // Section 1: Overview
        SectionCardWithRandomContent(
            title = "Overview",
            contentPool = textPool,
            itemEntity
        )

        // Section 2: Details
        SectionCardWithRandomContent(
            title = "Details",
            contentPool = textPool,
            itemEntity
        )

        // Section 3: Additional Information
        SectionCardWithRandomContent(
            title = "Additional Information",
            contentPool = textPool,
            itemEntity
        )
    }
}

@Composable
fun SectionCardWithRandomContent(title: String, contentPool: List<String>, itemEntity: ItemEntity) {
    val randomContent = contentPool.random()

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Title
            Text(
                text = "${itemEntity.id} $title",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Random Content
            Text(
                text = "${itemEntity.id} $randomContent",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Justify
            )
        }
    }
}

