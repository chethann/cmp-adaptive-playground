package io.github.chethann.cmp.adaptive.ui.screen.supportPane

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffold
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberSupportingPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun SupportingPaneScreen(
) {

    val navigator = rememberSupportingPaneScaffoldNavigator()

    SupportingPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        mainPane = {
            AnimatedPane(modifier = Modifier
                .background(Color.LightGray)
                .safeContentPadding()) {
                // Main pane content
                if (navigator.scaffoldValue[SupportingPaneScaffoldRole.Supporting] == PaneAdaptedValue.Hidden) {
                    Button(
                        modifier = Modifier.wrapContentSize(),
                        onClick = {
                            navigator.navigateTo(SupportingPaneScaffoldRole.Supporting)
                        }
                    ) {
                        Text("Go to supporting pane")
                    }
                } else {
                    Text("Supporting pane is shown")
                }
            }
        },
        supportingPane = {
            AnimatedPane {
                Column {
                    if (navigator.currentDestination?.pane == ThreePaneScaffoldRole.Secondary) {
                        Text("Go to main pane", modifier = Modifier.clickable {
                            navigator.navigateTo(SupportingPaneScaffoldRole.Main)
                        })
                    }

                    Text("Supporting pane")
                }
            }
        }
    )

}