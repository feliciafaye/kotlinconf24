package org.faye.kotlinconf

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Order() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Text",
            modifier = Modifier
                .background(Color(0XFF6804F6))
                .border(4.dp, Color(0XFFD30095))
                .padding(16.dp)
        )

        Box(
            modifier = Modifier
                .offset(50.dp)
                .scale(2F)
                .size(50.dp)
                .background(Color(0XFF6804F6))
        )
    }
}