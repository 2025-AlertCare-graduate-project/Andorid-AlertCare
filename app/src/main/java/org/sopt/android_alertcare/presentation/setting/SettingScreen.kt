package org.sopt.android_alertcare.presentation.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.sopt.android_alertcare.presentation.component.CustomDialog
import org.sopt.android_alertcare.presentation.component.TopBar
import org.sopt.android_alertcare.ui.theme.AlertTypography

@Composable
fun SettingsScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var showLogout by rememberSaveable { mutableStateOf(false) }
    var showWithdraw by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(Modifier.height(12.dp))
        TopBar(title = "설정")

        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("버전정보", color = Color.Black, style = AlertTypography.Bold20)
                Text("0.1.0", color = Color.Black, style = AlertTypography.Large20)
            }
            Text(
                text = "이용약관",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                color = Color.Black,
                style = AlertTypography.Bold20,

                )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                Text(
                    text = "로그아웃",
                    modifier = Modifier.clickable { showLogout = true },
                    color = Color.Black,
                    style = AlertTypography.Bold20
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                Text(
                    text = "탈퇴하기",
                    modifier = Modifier.clickable { showWithdraw = true },
                    color = Color.Black,
                    style = AlertTypography.Bold20
                )
            }
        }
    }

    if (showLogout) {
        CustomDialog(
            titleText = "로그아웃 하시겠어요?",
            subText = "",
            leftButtonText = "취소",
            rightButtonText = "로그아웃",
            onDismiss = { showLogout = false },
            onConfirm = { showLogout = false }
        )
    }

    if (showWithdraw) {
        CustomDialog(
            titleText = "정말 탈퇴하시겠어요?",
            subText = "계정과 데이터가 삭제될 수 있으며 복구가 어려울 수 있습니다.",
            leftButtonText = "취소",
            rightButtonText = "탈퇴",
            onDismiss = { showWithdraw = false },
            onConfirm = { showWithdraw = false }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsScreenPreview() {
//    SettingsScreen(
//        onTermsClick = { },
//        onLogoutClick = { },
//        onWithdrawClick = { }
//    )
}
