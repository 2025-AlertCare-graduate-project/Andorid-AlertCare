package org.sopt.android_alertcare.presentation.video

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.sopt.android_alertcare.core.common.ViewModelFactory
import org.sopt.android_alertcare.presentation.component.TopBar
import org.sopt.android_alertcare.presentation.signup.SignUpViewModel
import org.sopt.android_alertcare.presentation.util.UiState
import org.sopt.android_alertcare.ui.theme.AlertTypography
import org.sopt.android_alertcare.ui.theme.Orange

@Composable
fun VideoScreen(
    videoId: Long,
    navController: NavController,
    viewmodel: SignUpViewModel = viewModel(factory = ViewModelFactory()),
    onConfirmClick: () -> Unit = {}
) {
    val videoUrlState by viewmodel.videoUrlState.collectAsState()
    val videoCheckedState by viewmodel.videoCheckedState.collectAsState()

    LaunchedEffect(videoId) {
        viewmodel.fetchVideoUrl(videoId)
    }

    LaunchedEffect(videoCheckedState) {
        when (videoCheckedState) {
            is UiState.Success -> {
                onConfirmClick()
                navController.popBackStack()
            }

            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopBar("낙상 영상 확인")
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .navigationBarsPadding()
        ) {

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
                    text = "낙상 시점으로부터 앞,뒤 15초씩 녹화된 영상입니다.\n안전이 확인되면 아래의 확인 버튼을 눌러주세요.",
                    style = AlertTypography.Bold16,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (videoUrlState) {
                is UiState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is UiState.Success -> {
                    val videoUrl = (videoUrlState as UiState.Success<String>).data
                    VideoPlayer(
                        videoUrl = videoUrl,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.5f)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }

                is UiState.Error -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("영상을 불러오지 못했습니다.", color = Color.Red)
                    }
                }

                else -> {}
            }

            Spacer(modifier = Modifier.weight(1f))

            val isPatching = videoCheckedState is UiState.Loading


            Button(
                onClick = {
                    if (!isPatching) {
                        viewmodel.patchVideoChecked(videoId)
                        navController.popBackStack()
                        onConfirmClick()
                    }
                },
                enabled = !isPatching,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .navigationBarsPadding(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(Orange)
            ) {
                if (isPatching) {
                    CircularProgressIndicator(
                        strokeWidth = 2.dp,
                        color = Color.White
                    )
                } else {
                    Text(text = "확인 완료", fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
