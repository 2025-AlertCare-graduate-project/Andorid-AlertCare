package org.sopt.android_alertcare.ui.theme.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.android_alertcare.ui.theme.presentation.component.NextButton
import org.sopt.android_alertcare.ui.theme.presentation.component.TextFieldWithTitle
import org.sopt.android_alertcare.ui.theme.presentation.component.TopBar

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    val caregiverNameState = remember { mutableStateOf("") }
    val nameState = remember { mutableStateOf("") }
    val phoneState = remember { mutableStateOf("") }

    val step = remember { mutableStateOf(0) }

    val isPhoneValid = phoneState.value.length == 11 && phoneState.value.all { it.isDigit() }

    LaunchedEffect(isPhoneValid) {
        if (isPhoneValid && step.value < 1) step.value = 1
    }
    val isNameValid = nameState.value.isNotBlank()
    val isCaregiverNameValid = caregiverNameState.value.isNotBlank()

    val isFilled = isPhoneValid && isNameValid && isCaregiverNameValid

    LaunchedEffect(isPhoneValid) {
        if (isPhoneValid && step.value < 1) step.value = 1
    }
    LaunchedEffect(isNameValid) {
        if (isNameValid && step.value < 2 && step.value >= 1) step.value = 2
    }

    Column(
        modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar()
        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 10.dp)
        ) {
            AnimatedVisibility(visible = step.value >= 2) {
                Column {
                    TextFieldWithTitle(
                        titleText = "피보호자 성함을 입력해주세요",
                        hint = "성함을 입력해주세요",
                        maxLength = 5,
                        textState = caregiverNameState
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            AnimatedVisibility(visible = step.value >= 1) {
                Column {
                    TextFieldWithTitle(
                        titleText = "보호자 성함을 입력해주세요",
                        hint = "성함을 입력해주세요",
                        maxLength = 5,
                        textState = nameState
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            TextFieldWithTitle(
                titleText = "보호자 전화번호를 입력해주세요.",
                subText = "하이픈(-) 기호는 제외하고 숫자만 입력해주세요.",
                hint = "전화번호를 입력해주세요",
                maxLength = 11,
                textState = phoneState,
                isError = phoneState.value.isNotEmpty() && !isPhoneValid
            )

        }

        NextButton(
            text = "다음",
            isEnabled = isFilled,
            onClick = { TODO() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .navigationBarsPadding()
        )
    }
}


@Preview
@Composable
private fun SettingTagScreen() {
    LoginScreen()
}
