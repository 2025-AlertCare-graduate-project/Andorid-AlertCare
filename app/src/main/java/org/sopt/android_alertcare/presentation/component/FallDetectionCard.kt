package org.sopt.android_alertcare.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.android_alertcare.R
import org.sopt.android_alertcare.presentation.util.FallDetectionStatus
import org.sopt.android_alertcare.ui.theme.AlertTypography

@Composable
fun FallDetectionCard(
    status: FallDetectionStatus,
    dateTime: String,
    onVideoClick: () -> Unit
) {
    val logo = painterResource(id = R.drawable.ic_fall_down_person)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.Top) {
                Icon(
                    painter = logo,
                    contentDescription = "낙상 아이콘",
                    tint = Color(0xFFCB742E),
                    modifier = Modifier.size(20.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(
                        text = status.label,
                        fontSize = 16.sp,
                        style = AlertTypography.ExtraBold14
                    )
                    Spacer(modifier = Modifier.padding(top = 6.dp))

                    Text(
                        text = dateTime,
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                }
            }

            OutlinedButton(
                onClick = onVideoClick,
                enabled = status.buttonEnabled,
                shape = RoundedCornerShape(20.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = status.buttonBackgroundColor,
                    contentColor = status.buttonTextColor,
                    disabledContentColor = status.buttonTextColor
                ),
                border = BorderStroke(1.dp, status.buttonBorderColor)
            ) {
                Text(
                    text = status.buttonText,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FallDetectionCardPreview() {
    Column {
        FallDetectionCard(
            status = FallDetectionStatus.VIDEO_AVAILABLE,
            dateTime = "2025.05.21 22:23",
            onVideoClick = {}
        )

        FallDetectionCard(
            status = FallDetectionStatus.CHECKED,
            dateTime = "2025.05.21 22:23",
            onVideoClick = {}
        )

        FallDetectionCard(
            status = FallDetectionStatus.EXPIRED,
            dateTime = "2025.05.21 22:23",
            onVideoClick = {}
        )
    }
}

