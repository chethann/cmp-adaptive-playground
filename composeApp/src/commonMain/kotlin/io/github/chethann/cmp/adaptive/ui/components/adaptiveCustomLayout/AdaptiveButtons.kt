package io.github.chethann.cmp.adaptive.ui.components.adaptiveCustomLayout

/*
 * Copyright 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layoutId
import kotlin.math.max

/**
 * Show two buttons:
 * If there is room to show both when each fill up 50% of the available width, show them
 * horizontally side-by-side with each having 50% of the width.
 *
 * Otherwise, show them filling up the entire width on top of each other
 *
 * +-----------------------------+
 * |                             |
 * | ( Secondary ) (  Primary  ) |
 * |                             |
 * +-----------------------------+
 *
 * +----------------------------+
 * |                            |
 * | (         Primary        ) |
 * | (        Secondary       ) |
 * |                            |
 * +----------------------------+
 */
@Composable
fun AdaptiveButtons() {
    Layout(
        content = {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.layoutId("primary")
            ) {
                Text("PrimaryButtonTextTextTextText")
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.layoutId("secondary")
            ) {
                Text("SecondaryButtonTextTextTextText")
            }
        },
        measurePolicy = { measurables, constraints ->
            val primaryButtonMeasurable = measurables.find { it.layoutId == "primary" }!!
            val secondaryButtonMeasurable = measurables.find { it.layoutId == "secondary" }!!

            val primaryButtonMinIntrinsicWidth =
                primaryButtonMeasurable.minIntrinsicWidth(constraints.maxHeight)
            val secondaryButtonMinIntrinsicWidth =
                secondaryButtonMeasurable.minIntrinsicWidth(constraints.maxHeight)

            val showHorizontally = primaryButtonMinIntrinsicWidth <= constraints.maxWidth / 2 &&
                    secondaryButtonMinIntrinsicWidth <= constraints.maxWidth / 2

            val width = constraints.minWidth
            val height: Int
            val primaryButtonPlaceable: Placeable
            val secondaryButtonPlaceable: Placeable

            if (showHorizontally) {
                val halfWidthConstraints = constraints.copy(
                    minWidth = constraints.maxWidth / 2,
                    maxWidth = constraints.maxWidth / 2
                )

                primaryButtonPlaceable = primaryButtonMeasurable.measure(halfWidthConstraints)
                secondaryButtonPlaceable = secondaryButtonMeasurable.measure(halfWidthConstraints)

                height = max(primaryButtonPlaceable.height, secondaryButtonPlaceable.height)
            } else {
                val fullWidthConstraints = constraints.copy(
                    minWidth = constraints.maxWidth,
                )
                primaryButtonPlaceable = primaryButtonMeasurable.measure(fullWidthConstraints)
                secondaryButtonPlaceable = secondaryButtonMeasurable.measure(fullWidthConstraints)

                height = primaryButtonPlaceable.height + secondaryButtonPlaceable.height
            }

            layout(width, height) {
                if (showHorizontally) {
                    primaryButtonPlaceable.placeRelative(
                        width / 2,
                        (height - primaryButtonPlaceable.height) / 2
                    )
                    secondaryButtonPlaceable.placeRelative(
                        0,
                        (height - secondaryButtonPlaceable.height) / 2
                    )
                } else {
                    primaryButtonPlaceable.placeRelative(
                        0,
                        0
                    )
                    secondaryButtonPlaceable.placeRelative(
                        0,
                        primaryButtonPlaceable.height
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
}