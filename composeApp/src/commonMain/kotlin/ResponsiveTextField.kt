package org.faye.kotlinconf

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment

@Composable
fun ResponsiveTextField(
    textState: TextFieldValue,
    onTextChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    width: Int,
) {
    val density = LocalDensity.current.density
    val responsivePadding = (width / density * 0.06f + 0.5f).dp

    TextField(
        value = textState,
        onValueChange = onTextChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = responsivePadding)
            .height(60.dp)
            .background(Color.White, shape = androidx.compose.foundation.shape.RoundedCornerShape(4.dp))
            .clickable(interactionSource = MutableInteractionSource(), indication = null) {
                println("TextField clicked!")
            },
    )
}


@Composable
fun Demo4() {
    val textState = remember { mutableStateOf(TextFieldValue("Responsive Text Field")) }

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize().background(color = Color.Black).padding(16.dp)
    ) {
        val constraints = constraints
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier = Modifier
                    .size(height = 120.dp, width = 200.dp)
                    .border(2.dp, Color.Black),
                text = "Constraints: \nwidth\nmin:${constraints.minWidth}, max: ${constraints.maxWidth},\n" +
                        "height\nmin:${constraints.minHeight}, max:${constraints.maxHeight}"
            )
            Spacer(modifier = Modifier.height(16.dp))
            ResponsiveTextField(
                textState = textState.value,
                onTextChange = { newValue -> textState.value = newValue },
                width = constraints.maxWidth,
            )
        }
    }
}
