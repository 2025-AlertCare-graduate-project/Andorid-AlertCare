package org.sopt.android_alertcare.presentation.info.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.android_alertcare.ui.theme.AlertTypography
import org.sopt.android_alertcare.ui.theme.BaseOrange

@Composable
fun DailyBarChart(
    activeMinutes: Int,
    lyingMinutes: Int,
    sittingMinutes: Int,
    modifier: Modifier = Modifier
) {
    val values = listOf(activeMinutes, lyingMinutes, sittingMinutes)
    val labels = listOf("활동 시간", "누워있는 시간", "앉아있는 시간")
    val maxValue = values.maxOrNull() ?: 1

    // Y축 최대값을 시간 단위로 계산 (올림)
    val maxHours = (maxValue + 59) / 60  // 분을 시간으로 올림
    val displayMaxHours = when {
        maxHours <= 1 -> 1
        maxHours <= 3 -> 3
        maxHours <= 6 -> 6
        maxHours <= 10 -> 10
        else -> ((maxHours / 5) + 1) * 5
    }
    val displayMax = displayMaxHours * 60

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 40.dp, end = 16.dp, bottom = 40.dp)
                    .drawBehind {
                        val strokeWidth = 2.dp.toPx()

                        // Y축 그리기
                        drawLine(
                            color = Color.Black,
                            start = Offset(0f, 0f),
                            end = Offset(0f, size.height),
                            strokeWidth = strokeWidth
                        )

                        // X축 그리기
                        drawLine(
                            color = Color.Black,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = strokeWidth
                        )

                        // 화살표 (X축 끝)
                        val arrowSize = 8.dp.toPx()
                        drawLine(
                            color = Color.Black,
                            start = Offset(size.width, size.height),
                            end = Offset(size.width - arrowSize, size.height - arrowSize / 2),
                            strokeWidth = strokeWidth
                        )
                        drawLine(
                            color = Color.Black,
                            start = Offset(size.width, size.height),
                            end = Offset(size.width - arrowSize, size.height + arrowSize / 2),
                            strokeWidth = strokeWidth
                        )

                        // 화살표 (Y축 끝)
                        drawLine(
                            color = Color.Black,
                            start = Offset(0f, 0f),
                            end = Offset(-arrowSize / 2, arrowSize),
                            strokeWidth = strokeWidth
                        )
                        drawLine(
                            color = Color.Black,
                            start = Offset(0f, 0f),
                            end = Offset(arrowSize / 2, arrowSize),
                            strokeWidth = strokeWidth
                        )
                    }
            ) {
                // 막대 그래프
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Bottom
                ) {
                    values.forEachIndexed { index, value ->
                        BarItem(
                            value = value,
                            maxValue = displayMax,
                            color = BaseOrange,
                            showLabel = true,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .fillMaxHeight()
                    .padding(end = 8.dp, bottom = 40.dp, top = 20.dp)
            ) {
                // 시간 단위로만 표시
                for (i in displayMaxHours downTo 0) {
                    if (i > 0) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            Text(
                                text = "${i}시간",
                                style = AlertTypography.Regular10.copy(fontSize = 11.sp),
                                color = Color(0xFF999999),
                                textAlign = TextAlign.End,
                                modifier = Modifier
                                    .width(35.dp)
                                    .offset(y = (-8).dp)  // 약간 위로 올려서 선과 정렬
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier.height(0.dp),
                            contentAlignment = Alignment.BottomEnd
                        ) {
                            Text(
                                text = "0분",
                                style = AlertTypography.Regular10.copy(fontSize = 11.sp),
                                color = Color(0xFF999999),
                                textAlign = TextAlign.End,
                                modifier = Modifier
                                    .width(35.dp)
                                    .offset(y = (-2).dp)
                            )
                        }
                    }
                }
            }

            // X축 라벨
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(start = 40.dp, end = 16.dp, top = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                labels.forEach { label ->
                    Text(
                        text = label,
                        style = AlertTypography.Regular10.copy(fontSize = 11.sp),
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}
