package org.sopt.android_alertcare.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.sopt.android_alertcare.R
import org.sopt.android_alertcare.core.common.ViewModelFactory
import org.sopt.android_alertcare.domain.model.FallDetection
import org.sopt.android_alertcare.presentation.component.FallDetectionCard
import org.sopt.android_alertcare.presentation.component.MainAgeCard
import org.sopt.android_alertcare.presentation.component.MainColorCard
import org.sopt.android_alertcare.presentation.signup.SignUpViewModel
import org.sopt.android_alertcare.presentation.util.FallDetectionStatus
import org.sopt.android_alertcare.presentation.util.UiState
import org.sopt.android_alertcare.ui.theme.AlertTypography
import timber.log.Timber

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    phoneNumber: String,
    careReceiverName: String,
    viewmodel: SignUpViewModel = viewModel(factory = ViewModelFactory()),

    ) {

    val videoListState by viewmodel.videoListState.collectAsState()

    // 최초 진입 시 비디오 리스트 가져오기
    LaunchedEffect(Unit) {
        viewmodel.fetchVideoList(phoneNumber)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Image(
                alignment = Alignment.Center,
                painter = painterResource(id = R.drawable.ic_profile_grandmother),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row {
                Text(
                    text = careReceiverName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "님 ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            HorizontalDivider(
                modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth(),
                thickness = 7.dp, color = Color(0xFFC9E9FF),
            )

            Column(Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.padding(start = 18.dp, top = 18.dp),
                    text = "최근 알람 확인하기",
                    style = AlertTypography.ExtraBold20,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(4.dp))
                MainColorCard(
                    modifier = modifier.padding(horizontal = 12.dp),
                    text = "낙상 후 12시간 지난 영상은 확인 불가능합니다.",
                    isIcon = true,
                )
            }

            Spacer(modifier = Modifier.height(12.dp))


            when (val state = videoListState) {
                is UiState.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                    ) {
                        items(state.data) { video ->
                            Spacer(modifier = Modifier.height(10.dp))
                            FallDetectionCard(
                                status = FallDetectionStatus.from(video.videoAccessible, video.checkedByUser),
                                dateTime = video.fallDetectTime,
                                onVideoClick = {
                                    val status = FallDetectionStatus.from(video.videoAccessible, video.checkedByUser)
                                    if (status != FallDetectionStatus.EXPIRED) {
                                        navController.navigate("video_screen/${video.id}")
                                        Timber.tag("df").d(video.id.toString())
                                    }
                                }
                            )
                        }
                    }
                }

                is UiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }

                is UiState.Error -> {
                    Text(
                        text = "영상 데이터를 불러오지 못했습니다.",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                else -> {}
            }

        }
    }
}
