/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.wear.tiles.messaging.tile

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.wear.protolayout.ColorBuilders
import androidx.wear.protolayout.DeviceParametersBuilders
import androidx.wear.protolayout.LayoutElementBuilders
import androidx.wear.protolayout.ResourceBuilders.Resources
import androidx.wear.protolayout.material.Text
import androidx.wear.protolayout.material.Typography
import androidx.wear.protolayout.material.layouts.PrimaryLayout
import com.example.wear.tiles.R
import com.example.wear.tiles.messaging.Contact
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.tiles.images.drawableResToImageResource
import com.google.android.horologist.tiles.images.toImageResource
import com.google.android.horologist.tiles.render.SingleTileLayoutRenderer

@ExperimentalHorologistApi
class MessagingTileRenderer(context: Context) :
    SingleTileLayoutRenderer<MessagingTileState, Map<Contact, Bitmap>>(context) {

    override fun renderTile(
        state: MessagingTileState,
        deviceParameters: DeviceParametersBuilders.DeviceParameters
    ): LayoutElementBuilders.LayoutElement {
        return messagingTileLayout(
            context = context,
            deviceParameters = deviceParameters,
            state = state
        )
    }

    override fun Resources.Builder.produceRequestedResources(
        resourceState: Map<Contact, Bitmap>,
        deviceParameters: DeviceParametersBuilders.DeviceParameters,
        resourceIds: List<String>
    ) {
        addIdToImageMapping(ID_IC_SEARCH, drawableResToImageResource(R.drawable.ic_search_24))

        resourceState.forEach { (contact, bitmap) ->
            addIdToImageMapping(
                /* id = */ contact.imageResourceId(),
                /* image = */ bitmap.toImageResource()
            )
        }
    }

    companion object {
        internal const val ID_IC_SEARCH = "ic_search"
    }

}

/**
 * Layout definition for the Messaging Tile.
 */
@ExperimentalHorologistApi
private fun messagingTileLayout(
    context: Context,
    deviceParameters: DeviceParametersBuilders.DeviceParameters,
    state: MessagingTileState
) = PrimaryLayout.Builder(deviceParameters)
    .setResponsiveContentInsetEnabled(true)
    .setContent(
        Text.Builder(context, context.getString(R.string.hello_tile_body))
            .setTypography(Typography.TYPOGRAPHY_BODY1)
            .setColor(ColorBuilders.argb(Color.White.toArgb()))
            .build()
    )
    .build()
