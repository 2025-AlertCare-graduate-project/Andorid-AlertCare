package org.sopt.android_alertcare.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.sopt.android_alertcare.R
import org.sopt.android_alertcare.core.common.ViewModelFactory
import org.sopt.android_alertcare.presentation.component.FallDetectionCard
import org.sopt.android_alertcare.presentation.component.MainColorCard
import org.sopt.android_alertcare.presentation.component.TopBarWithIcon
import org.sopt.android_alertcare.presentation.navigation.ScreenRoute
import org.sopt.android_alertcare.presentation.signup.SignUpViewModel
import org.sopt.android_alertcare.presentation.util.FallDetectionStatus
import org.sopt.android_alertcare.presentation.util.UiState
import org.sopt.android_alertcare.ui.theme.AlertTypography
import org.sopt.android_alertcare.ui.theme.Orange
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    phoneNumber: String ,
    careReceiverName: String,
    viewmodel: SignUpViewModel = viewModel(factory = ViewModelFactory()),
) {
    val videoListState by viewmodel.videoListState.collectAsState()

    LaunchedEffect(Unit) { viewmodel.fetchVideoList(phoneNumber) }

    val refreshState = rememberPullToRefreshState()
    LaunchedEffect(refreshState.isRefreshing) {
        if (refreshState.isRefreshing) {
            viewmodel.fetchVideoList(phoneNumber)
            refreshState.endRefresh()
        }
    }
    val showIndicator by remember {
        derivedStateOf { refreshState.isRefreshing || refreshState.progress > 0f }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Spacer(Modifier.height(12.dp))
        TopBarWithIcon(
            "메인페이지",
            iconLeft = painterResource(id = R.drawable.ic_setting),
            iconRight = painterResource(id = R.drawable.ic_graph),
            onIconLeftClick = { navController.navigate(ScreenRoute.SETTING_SCREEN) },
            onIconRightClick = {
                navController.navigate("${ScreenRoute.DAILY_CHART_SCREEN}/$phoneNumber")

            }
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .statusBarsPadding()
                .navigationBarsPadding()
                .nestedScroll(refreshState.nestedScrollConnection)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    bottom = 16.dp
                )
            ) {
                item {
                    Spacer(Modifier.height(30.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_profile_grandmother),
                            contentDescription = null
                        )

                        Spacer(Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = careReceiverName,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "님 ",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(Modifier.height(32.dp))
                    }

                    HorizontalDivider(thickness = 7.dp, color = Orange.copy(alpha = 0.4f))

                    Column(Modifier.fillMaxWidth()) {
                        Text(
                            modifier = Modifier.padding(start = 18.dp, top = 18.dp),
                            text = "최근 알람 확인하기",
                            style = AlertTypography.ExtraBold20,
                            textAlign = TextAlign.Start
                        )
                        Spacer(Modifier.height(10.dp))
                        MainColorCard(
                            modifier = Modifier.padding(horizontal = 12.dp),
                            text = "낙상 후 12시간 지난 영상은 확인 불가능합니다.",
                            isIcon = true,
                        )
                    }

                    Spacer(Modifier.height(6.dp))
                }

                when (val state = videoListState) {
                    is UiState.Success -> {
                        items(state.data) { video ->
                            FallDetectionCard(
                                status = FallDetectionStatus.from(
                                    video.videoAccessible,
                                    video.checkedByUser
                                ),
                                dateTime = video.fallDetectTime,
                                onVideoClick = {
                                    val status = FallDetectionStatus.from(
                                        video.videoAccessible,
                                        video.checkedByUser
                                    )
                                    if (status != FallDetectionStatus.EXPIRED) {
                                        navController.navigate("video_screen/${video.id}/${phoneNumber}/${careReceiverName}")
                                        Timber.tag("df").d(video.id.toString())
                                    }
                                }
                            )
                        }
                    }

                    is UiState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 24.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }

                    is UiState.Error -> {
                        item {
                            Text(
                                text = "영상 데이터를 불러오지 못했습니다.",
                                color = Color.Red,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 12.dp),
                            )
                        }
                    }

                    else -> {}
                }
            }

            if (showIndicator) {
                PullToRefreshContainer(
                    state = refreshState,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        }
    }
}
