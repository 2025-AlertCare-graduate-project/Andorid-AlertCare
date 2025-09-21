package org.sopt.android_alertcare.presentation.info.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.sopt.android_alertcare.presentation.util.formatMinutes
import org.sopt.android_alertcare.ui.theme.AlertTypography
import kotlin.math.max

@Composable
fun BarItem(
    value: Int,
    maxValue: Int,
    color: Color,
    showLabel: Boolean = true,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 8.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            val heightFraction = if (maxValue > 0) {
                max(0.02f, value.toFloat() / maxValue.toFloat())
            } else 0.02f

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom
            ) {
                // 값 표시 (막대 위)
                if (showLabel) {
                    Text(
                        text = formatMinutes(value),
                        style = AlertTypography.Bold14,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .width(60.dp)
                        .fillMaxHeight(heightFraction)
                        .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                        .background(color)
                )
            }
        }
    }
}
