package org.sopt.android_alertcare.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.android_alertcare.R
import org.sopt.android_alertcare.ui.theme.Orange


@Composable
fun MainColorCard(
    text: String,
    textColor: Color = Color.Black,
    roundShape: Int = 20,
    isIcon: Boolean = false,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Orange.copy(alpha = 0.15f),
                shape = RoundedCornerShape(roundShape)
            )
            .padding(vertical = 6.dp)
            .padding(start = 12.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isIcon) {
            Image(
                alignment = Alignment.Center,
                painter = painterResource(id = R.drawable.ic_warning),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.padding(start = 6.dp))

        Text(
            text = text,
            color = textColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

    }
}

@Preview
@Composable
private fun PreviewScreen() {
    MainAgeCard("87")
}

