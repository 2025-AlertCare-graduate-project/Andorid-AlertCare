package org.sopt.android_alertcare.ui.theme.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.sopt.android_alertcare.R
import org.sopt.android_alertcare.ui.theme.presentation.component.NextButton
import org.sopt.android_alertcare.ui.theme.presentation.navigation.ScreenRoute


@Composable
fun LoginCompleteScreen(
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
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "회원가입 완료",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        NextButton(
            text = "시작하기",
            isEnabled = true,
            onClick = { navController.navigate(ScreenRoute.MAIN_SCREEN) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 24.dp)
                .navigationBarsPadding()
        )
    }
}
