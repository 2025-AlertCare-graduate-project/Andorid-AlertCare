package org.sopt.android_alertcare.presentation.video

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.android_alertcare.presentation.util.FallDetectionStatus

@Composable
fun FallDetectionVideoScreen(
    fallTime: String,
    status: FallDetectionStatus,
    onConfirmClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "낙상 영상 확인",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFFBE9E7), shape = RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Text(
                text = "낙상 시점으로부터 앞,뒤 15초씩 녹화된 영상입니다.\n안전이 확인되면 아래의 ‘${status.buttonText}’ 버튼을 눌러주세요.",
                fontSize = 14.sp,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "낙상 시점 : $fallTime",
            fontSize = 14.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(8.dp))
        ) {
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onConfirmClick,
            enabled = status.buttonEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = status.buttonBackgroundColor,
                contentColor = status.buttonTextColor,
                disabledContainerColor = status.buttonBackgroundColor,
                disabledContentColor = status.buttonTextColor
            )
        ) {
            Text(
                text = "확인 완료",
                fontSize = 16.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FallDetectionVideoScreenPreview() {
    Column {
        FallDetectionVideoScreen(
            fallTime = "2025.07.30 18:43",
            status = FallDetectionStatus.VIDEO_AVAILABLE,
            onConfirmClick = {}
        )
        Spacer(modifier = Modifier.height(16.dp))
        FallDetectionVideoScreen(
            fallTime = "2025.07.30 18:43",
            status = FallDetectionStatus.CHECKED
        )
        Spacer(modifier = Modifier.height(16.dp))
        FallDetectionVideoScreen(
            fallTime = "2025.07.30 18:43",
            status = FallDetectionStatus.EXPIRED
        )
    }
}
