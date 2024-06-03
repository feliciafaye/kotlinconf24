package org.faye.kotlinconf

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Focus(
    amount: Int,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier,
    ) {
        items(count = amount) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .size(175.dp)
                    .background(colors[it % colors.size])
                    .focusProperties { canFocus = false } // must be applied before .clickable
                    .clickable {
                        println("Item $it")
                    },
                contentAlignment = Alignment.Center,
            ) {
                Text("Item ${it + 1}")
            }
            if (it < amount - 1) {
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}


@Preview
@Composable
private fun FocusScreenPreview() {
    MaterialTheme {
        Focus(5)
    }
}

private val colors = listOf(Color.LightGray, Color.Gray, Color.DarkGray, Color.Red, Color.Blue)