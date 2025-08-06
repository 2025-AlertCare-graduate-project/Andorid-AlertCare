package org.sopt.android_alertcare.presentation.util

import androidx.compose.ui.graphics.Color
import org.sopt.android_alertcare.ui.theme.MainOrange
import org.sopt.android_alertcare.ui.theme.Orange


enum class FallDetectionStatus(
    val label: String,
    val buttonText: String,
    val buttonEnabled: Boolean,
    val buttonTextColor: Color,
    val buttonBorderColor: Color,
    val buttonBackgroundColor: Color
) {
    /**
     * 영상 보기 가능 (아직 확인 안 함)
     */
    VIDEO_AVAILABLE(
        label = "낙상 감지",
        buttonText = "영상 보기",
        buttonEnabled = true,
        buttonTextColor = MainOrange,
        buttonBorderColor = Orange,
        buttonBackgroundColor = Color(0xFFFFF1E8)
    ),

    /**
     * 사용자가 확인 완료함
     */
    CHECKED(
        label = "낙상 감지",
        buttonText = "확인 완료",
        buttonEnabled = false,
        buttonTextColor = Color.Gray,
        buttonBorderColor = Color.LightGray,
        buttonBackgroundColor = Color(0xFFF5F5F5)
    ),

    /**
     * 영상 접근 불가 (ex. 만료)
     */
    EXPIRED(
        label = "낙상 감지",
        buttonText = "만료됨",
        buttonEnabled = false,
        buttonTextColor = Color.Gray,
        buttonBorderColor = Color.LightGray,
        buttonBackgroundColor = Color(0xFFF5F5F5)
    );

    companion object {
        fun from(videoAccessible: Boolean, checkedByUser: Boolean): FallDetectionStatus {
            return when {
                videoAccessible && !checkedByUser -> VIDEO_AVAILABLE
                videoAccessible && checkedByUser -> CHECKED
                else -> EXPIRED
            }
        }
    }
}
