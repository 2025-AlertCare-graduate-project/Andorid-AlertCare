package org.sopt.android_alertcare.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.android_alertcare.R
import org.sopt.android_alertcare.ui.theme.AlertTypography
import org.sopt.android_alertcare.ui.theme.MainOrange
import org.sopt.android_alertcare.ui.theme.Orange

@Composable
fun SplashScreen(
    onLoginClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {}
) {
    val logo = painterResource(id = R.drawable.ic_logo)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF9F5))
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(64.dp))

        Image(
            painter = logo,
            contentDescription = "AlertCare Logo",
            modifier = Modifier.padding(top = 180.dp)
        )
        Spacer(Modifier.height(30.dp))
        Text(text = "알랏케어", style = AlertTypography.ExtraBold65, color = MainOrange)
        Spacer(Modifier.height(14.dp))
        Text(text = "AlertCare", color = Orange, style = AlertTypography.Large40)

        Spacer(Modifier.weight(1f))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEBA165))
            ) {
                Text(text = "로그인하여 시작하기", color = Color.White, style = AlertTypography.Bold20)
            }

            Spacer(Modifier.height(16.dp))

            Row {
                Text(text = "AlertCare가 처음이신가요? |", color = Color.Gray, fontSize = 14.sp)
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "회원가입",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable { onSignUpClick() }
                )
            }
            Spacer(Modifier.height(32.dp))
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlertCareStartScreenPreview() {
    SplashScreen()
}
