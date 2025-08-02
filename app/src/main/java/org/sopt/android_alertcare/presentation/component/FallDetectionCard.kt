package org.sopt.android_alertcare.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FallDetectionCard(
    modifier: Modifier = Modifier,
    fallDownDate: String,
    fallDownTime: String,
    isVideoAccessible: Boolean = false,
    onVideoClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .background(Color.White)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(10.dp)
                .background(Color(0xFF7ECBFF))
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "낙상 감지",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "\uD83D\uDCCD",
                    style = TextStyle(fontSize = 12.sp),
                    color = Color.DarkGray
                )

                Text(
                    text = "${fallDownDate} ${fallDownTime} 발생",
                    style = TextStyle(fontSize = 12.sp),
                    color = Color.DarkGray
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
        }

        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = onVideoClick,
            modifier = Modifier
                .alpha(if (isVideoAccessible) 1f else 0f)
                .align(Alignment.CenterVertically),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFC9E9FF),
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(20.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            elevation = null
        ) {
            Text(text = "영상 확인", fontSize = 14.sp)
        }
    }
}

@Preview
@Composable
private fun PreviewScreen() {
    FallDetectionCard(
        fallDownDate = "2025.06.18",
        fallDownTime = "22:23",
        isVideoAccessible = true,
        onVideoClick = { }
    )
}
