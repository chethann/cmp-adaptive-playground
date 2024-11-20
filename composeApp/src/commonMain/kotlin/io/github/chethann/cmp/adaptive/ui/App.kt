package io.github.chethann.cmp.adaptive.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.chethann.cmp.adaptive.ui.screen.adaptive.AdaptiveScreen
import io.github.chethann.cmp.adaptive.ui.screen.feed.FeedScreen
import io.github.chethann.cmp.adaptive.ui.screen.listDetails.ListDetailScreen
import io.github.chethann.cmp.adaptive.ui.screen.navigation.AdaptiveNavigationScreen
import io.github.chethann.cmp.adaptive.ui.screen.supportPane.SupportingPaneScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        AppContent()
    }
}

@Composable
fun AppContent() {

    val navController = rememberNavController()

    Column {

        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("back")
        }

        NavHost(
            navController = navController,
            startDestination = "home",
        ) {

            composable("home") {
                HomeScreen(navController)
            }

            composable("ListDetailScreen") {
                ListDetailScreen()
            }

            composable("FeedScreen") {
                FeedScreen()
            }

            composable("SupportingPaneScreen") {
                SupportingPaneScreen()
            }

            composable("AdaptiveNavigationBar") {
                AdaptiveNavigationScreen()
            }

            composable("AdaptiveScreen") {
                AdaptiveScreen()
            }


        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {

    Column {
        Button(onClick = {
            navController.navigate("ListDetailScreen")
        }) {
            Text("ListDetailScreen")
        }

        Button(onClick = {
            navController.navigate("SupportingPaneScreen")
        }) {
            Text("SupportingPaneScreen")
        }

        Button(onClick = {
            navController.navigate("AdaptiveNavigationBar")
        }) {
            Text("AdaptiveNavigationBar")
        }

        // Till here all are standard, there is not much we can do for custom usecase.
        // Below ones give more control

        Button(onClick = {
            navController.navigate("FeedScreen")
        }) {
            Text("FeedScreen")
        }

        Button(onClick = {
            navController.navigate("AdaptiveScreen")
        }) {
            Text("AdaptiveScreen")
        }

        Button(onClick = {
            navController.navigate("Final")
        }) {
            Text("Final")
        }

    }

}