package io.github.chethann.cmp.adaptive.ui.screen.navigation

import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import io.github.chethann.cmp.adaptive.ui.components.adaptiveCustomLayout.AdaptiveButtons

@Composable
fun AdaptiveNavigationScreen() {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    var selectedItem by remember { mutableStateOf(0) }

    // Can use NavigationSuiteScaffold as well to achieve the same

    val items = listOf(
        NavigationItem("Home", Icons.Default.Home),
        NavigationItem("Favorites", Icons.Default.Star),
        NavigationItem("Settings", Icons.Default.Settings)
    )

    when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            // Use Bottom Navigation for small screens
            BottomNavigationContent(
                items = items,
                selectedItem = selectedItem,
                onItemSelected = { selectedItem = it }
            )
        }
        WindowWidthSizeClass.MEDIUM -> {
            // Use Navigation Rail for larger screens
            NavigationRailContent(
                items = items,
                selectedItem = selectedItem,
                onItemSelected = { selectedItem = it }
            )
        }
        else -> {
            // Use Navigation Rail for larger screens
            NavigationDrawerContent(
                items = items,
                selectedItem = selectedItem,
                onItemSelected = { selectedItem = it }
            )
        }
    }
}

@Composable
fun BottomNavigationContent(
    items: List<NavigationItem>,
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = { onItemSelected(index) },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Content(items[selectedItem])
    }
}

@Composable
fun NavigationRailContent(
    items: List<NavigationItem>,
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {
    Scaffold { innerPadding ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            NavigationRail {
                items.forEachIndexed { index, item ->
                    NavigationRailItem(
                        selected = selectedItem == index,
                        onClick = { onItemSelected(index) },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
            Content(items[selectedItem])
        }
    }
}

@Composable
fun NavigationDrawerContent(
    items: List<NavigationItem>,
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {
    Scaffold { innerPadding ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            NavigationRail(
                modifier = Modifier.width(240.dp)
            ) {
                items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        selected = selectedItem == index,
                        onClick = { onItemSelected(index) },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
            Content(items[selectedItem])
        }
    }
}

@Composable
fun Content(item: NavigationItem) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Selected: ${item.label}",
            style = MaterialTheme.typography.bodyLarge
        )

        AdaptiveButtons()
    }
}

data class NavigationItem(val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)


