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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.sopt.android_alertcare.R
import org.sopt.android_alertcare.domain.model.FallDetection
import org.sopt.android_alertcare.presentation.component.FallDetectionCard
import org.sopt.android_alertcare.presentation.component.MainAgeCard
import org.sopt.android_alertcare.presentation.component.MainColorCard

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {


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
                    text = "주효은",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "님 ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            MainAgeCard("87")

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
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(4.dp))
                MainColorCard(
                    modifier = modifier.padding(horizontal = 12.dp),
                    text = "낙상 시점으로부터 1시간 지난 영상은 확인 불가능합니다."
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                items(dummyFallDetectionList) { fall ->
                    Spacer(modifier = Modifier.height(10.dp))
                    FallDetectionCard(
                        fallDownDate = fall.fallDate,
                        fallDownTime = fall.fallTime,
                        isVideoAccessible = fall.isVideoAccessible,
                        onVideoClick = { }
                    )
                }
            }
        }
    }
}


val dummyFallDetectionList = listOf(
    FallDetection(fallDate = "2025.05.21", fallTime = "22:23", isVideoAccessible = true),
    FallDetection(fallDate = "2025.05.21", fallTime = "21:45", isVideoAccessible = true),
    FallDetection(fallDate = "2025.05.21", fallTime = "20:30", isVideoAccessible = false)
)

//
//@Preview
//@Composable
//private fun PreviewScreen() {
//    MainScreen()
//}
