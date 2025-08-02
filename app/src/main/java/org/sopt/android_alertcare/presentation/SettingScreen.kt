package org.sopt.android_alertcare.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.android_alertcare.presentation.component.TopBar

@Composable
fun SettingsScreen(
    onTermsClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onWithdrawClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar(title = "설정")

        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("버전정보", color = Color.Black)
                Text("0.1.0", color = Color.Black)
            }


            Text(
                text = "이용약관",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onTermsClick() }
                    .padding(vertical = 12.dp),
                color = Color.Black
            )


            Text(
                text = "로그아웃",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onLogoutClick() }
                    .padding(vertical = 12.dp),
                color = Color.Black
            )


            Text(
                text = "탈퇴하기",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onWithdrawClick() }
                    .padding(vertical = 12.dp),
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(
        onTermsClick = { },
        onLogoutClick = { },
        onWithdrawClick = { }
    )
}
