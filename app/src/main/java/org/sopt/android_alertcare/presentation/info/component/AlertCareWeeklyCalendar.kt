package org.sopt.android_alertcare.presentation.info.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.android_alertcare.presentation.util.noRippleClickable
import org.sopt.android_alertcare.ui.theme.AlertTypography
import org.sopt.android_alertcare.ui.theme.BaseOrange
import org.sopt.android_alertcare.ui.theme.Orange
import java.time.LocalDate


@Composable
fun AlertCareWeeklyCalendar(
    selectedDate: LocalDate,
    onDateClick: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val today = LocalDate.now()
    val startOfWeek = selectedDate.minusDays(selectedDate.dayOfWeek.value.toLong() % 7)

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val daysOfWeek = listOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT")

        (0..6).forEach { offset ->
            val currentDate = startOfWeek.plusDays(offset.toLong())
            val dayOfWeek = daysOfWeek[offset]
            val isSelected = currentDate == selectedDate
            val isToday = currentDate == today

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .noRippleClickable { onDateClick(currentDate) }
            ) {
                Text(
                    text = dayOfWeek,
                    style = AlertTypography.Regular10.copy(fontSize = 12.sp),
                    color = if (isSelected) Orange else Color(0xFF999999)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(32.dp)
                        .background(
                            color = if (isSelected) BaseOrange else Color.Transparent,
                            shape = CircleShape
                        )
                ) {
                    Text(
                        text = currentDate.dayOfMonth.toString(),
                        style = if (isSelected) AlertTypography.Bold14 else AlertTypography.Regular14,
                        color = if (isSelected) Color.White else if (isToday) Orange else Color.Black
                    )
                }
            }
        }
    }
}
