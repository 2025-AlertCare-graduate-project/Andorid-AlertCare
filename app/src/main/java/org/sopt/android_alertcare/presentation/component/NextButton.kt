package org.sopt.android_alertcare.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NextButton(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isEnabled) Color(0xFF90CAF9) else Color(0xFFE0E0E0)

    Button(
        onClick = onClick,
        enabled = isEnabled,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = Color.White,
            disabledContainerColor = Color(0xFFE0E0E0),
            disabledContentColor = Color.White
        ),
        elevation = null
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        )
    }
}
