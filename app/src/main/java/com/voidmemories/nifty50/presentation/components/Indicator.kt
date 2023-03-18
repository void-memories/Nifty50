package com.voidmemories.nifty50.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Indicator(isIncreasing: Boolean) {
    Box(
        Modifier
            .height(16.dp)
            .width(16.dp)
            .background(
                color =
                if (isIncreasing)
                    Color.Green
                else Color.Red, shape = RoundedCornerShape(50)
            )
    ) {
        Icon(
            Icons.Filled.ArrowDropDown, null,
            modifier = Modifier.rotate(
                if (isIncreasing)
                    180F
                else
                    0F
            ),
        )
    }
}