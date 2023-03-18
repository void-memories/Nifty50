package com.voidmemories.nifty50.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.voidmemories.nifty50.data.models.StockModel
import kotlin.math.abs

@Composable
fun ComposableCard(stockObject: StockModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .border(2.dp, MaterialTheme.colors.secondary, shape = RoundedCornerShape(12.dp))

    ) {
        Column(Modifier.padding(12.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(color = Color.Black, shape = RoundedCornerShape(4.dp))
                ) {
                    Text(
                        text = stockObject.symbol,
                        Modifier.padding(4.dp),
                        style = MaterialTheme.typography.body1
                    )
                }
                Text("₹${stockObject.lastPrice}", style = MaterialTheme.typography.h2)
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stockObject.identifier, style = MaterialTheme.typography.h2)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "${abs(stockObject.change)} (${abs(stockObject.pChange)}%)",
                        Modifier.padding(end = 8.dp), style = MaterialTheme.typography.body2
                    )
                    Indicator(isIncreasing = stockObject.change >= 0)
                }
            }
        }
    }
}