package org.sopt.android_alertcare.presentation.info


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.sopt.android_alertcare.core.common.ViewModelFactory
import org.sopt.android_alertcare.domain.model.DailyChart
import org.sopt.android_alertcare.presentation.component.TopBar
import org.sopt.android_alertcare.presentation.info.component.AlertCareWeeklyCalendar
import org.sopt.android_alertcare.presentation.info.component.DailyBarChart
import org.sopt.android_alertcare.presentation.signup.SignUpViewModel
import org.sopt.android_alertcare.presentation.util.UiState
import org.sopt.android_alertcare.ui.theme.AlertTypography
import org.sopt.android_alertcare.ui.theme.Orange
import java.time.LocalDate


@Composable
fun DailyChartScreen(
    phoneNumber: String,
    navController: NavController,
    viewModel: SignUpViewModel = viewModel(factory = ViewModelFactory()),
    onConfirmClick: () -> Unit = {}
) {
    val dailyChartState by viewModel.dailyChartState.collectAsState()
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    LaunchedEffect(selectedDate) {
        viewModel.getDailyChart(phoneNumber, selectedDate)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()

    ) {
        Spacer(modifier = Modifier.height(12.dp))

        TopBar("${selectedDate.monthValue}/${selectedDate.dayOfMonth} 일일 활동량")
        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
        ) {
            AlertCareWeeklyCalendar(
                selectedDate = selectedDate,
                onDateClick = { newDate ->
                    selectedDate = newDate
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 10.dp)
            )

            HorizontalDivider(
                color = Orange.copy(alpha = 0.4f),
                thickness = 1.dp
            )

            when (dailyChartState) {
                is UiState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Orange)
                    }
                }

                is UiState.Success -> {
                    val dailyChart = (dailyChartState as UiState.Success<DailyChart>).data

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(horizontal = 20.dp)
                    ) {
                        Spacer(modifier = Modifier.height(24.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = Orange.copy(alpha = 0.15f),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(12.dp)
                        ) {
                            Text(
                                text = "매일 아침 7시에 전날의 활동 리포트가 업데이트 됩니다.",
                                style = AlertTypography.Bold14,
                                color = Color.Black
                            )
                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        DailyBarChart(
                            activeMinutes = dailyChart.activeTime,
                            lyingMinutes = dailyChart.lyingTime,
                            sittingMinutes = dailyChart.sittingTime,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                        )

                        Spacer(modifier = Modifier.height(40.dp))

                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "AI 한 줄 리포트",
                                style = AlertTypography.Bold18,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        color = Color(0xFFFFF4EC),
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = dailyChart.dailySummaryText,
                                    style = AlertTypography.Regular14,
                                    color = Color.Black,
                                    lineHeight = 20.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Button(
                            onClick = {
                                onConfirmClick()
                                navController.popBackStack()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Orange
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "확인 완료",
                                style = AlertTypography.Bold16,
                                color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }

                is UiState.Error -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(horizontal = 32.dp)
                        ) {
                            Text(
                                text = "데이터를 불러올 수 없습니다",
                                style = AlertTypography.Bold16,
                                color = Color.Black
                            )

                        }
                    }
                }

                is UiState.Empty -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(horizontal = 32.dp)
                        ) {
                            Text(
                                text = "존재하지 않는 데이터 입니다",
                                style = AlertTypography.Bold16,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                        }
                    }
                }
            }
        }
    }
}
