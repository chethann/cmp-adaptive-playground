package io.github.chethann.cmp.adaptive.ui.screen.listDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldDestinationItem
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListDetailScreen() {

    val navigator = rememberListDetailPaneScaffoldNavigator<ItemEntity>()
    val items = remember { getItems() }
    val selectedItem = remember { mutableStateOf<ItemEntity?>(null) }

    LaunchedEffect(navigator.currentDestination) {
        if (navigator.currentDestination?.content == null) {
            selectedItem.value = null
        }
    }

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        modifier = Modifier.onKeyEvent {
            if (it.key == Key.A && it.type == KeyEventType.KeyDown) {
                println("A pressed")
            }
            return@onKeyEvent true
        },
        listPane = {
            AnimatedPane {
                LazyColumn {
                    items(items) {
                        SimpleCard(
                            itemEntity = it,
                            onCLick = { item ->
                                selectedItem.value = item
                                navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, item)
                            },
                            selectedItem.value
                        )
                    }
                    }
                }
        },
        detailPane = {
            AnimatedPane {
                navigator.currentDestination?.content?.let {
                    DetailsViewWithRandomTexts(it, navigator)
                } ?: Text("Select something to see deatils")
            }
        },
        extraPane = {
            AnimatedPane {
                navigator.currentDestination?.content?.let {
                    Column(
                        modifier = Modifier
                            .background(Color.Green)
                    ) {
                        Text("Extra Pane!")
                        Text("Go Back", modifier = Modifier
                            .clickable { navigator.navigateBack() })
                    }
                } ?: Text("Empty")
            }
        }
    )
}

@OptIn(ExperimentalUuidApi::class)
@Composable
fun SimpleCard(itemEntity: ItemEntity, onCLick: (ItemEntity) -> Unit, selectedItem: ItemEntity?) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable {
                onCLick(itemEntity)
            },

        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors().copy(containerColor = if (selectedItem?.id == itemEntity.id) Color.Magenta else CardDefaults.cardColors().containerColor),
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
                textAlign = TextAlign.Justify
            )
        }
    }
}


data class ItemEntity(val id: String)

@OptIn(ExperimentalUuidApi::class)
fun getItems(): List<ItemEntity> {
    return (0..100).map {
        ItemEntity(Uuid.random().toString())
    }
}