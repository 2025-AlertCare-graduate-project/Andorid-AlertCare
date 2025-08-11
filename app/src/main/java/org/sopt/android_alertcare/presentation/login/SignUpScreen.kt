package org.sopt.android_alertcare.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.firebase.messaging.FirebaseMessaging
import org.sopt.android_alertcare.core.common.ViewModelFactory
import org.sopt.android_alertcare.domain.model.SignUp
import org.sopt.android_alertcare.common.FcmViewModel
import org.sopt.android_alertcare.presentation.signup.SignUpViewModel
import org.sopt.android_alertcare.presentation.component.NextButton
import org.sopt.android_alertcare.presentation.component.TextFieldWithTitle
import org.sopt.android_alertcare.presentation.component.TopBar
import org.sopt.android_alertcare.presentation.navigation.ScreenRoute
import timber.log.Timber

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewmodel: SignUpViewModel = viewModel(factory = ViewModelFactory()),
    fcmViewModel: FcmViewModel = viewModel(factory = ViewModelFactory())

) {
    val careGiverNameState = remember { mutableStateOf("") }
    val phoneState = remember { mutableStateOf("") }
    val careReceiverNameState = remember { mutableStateOf("") }
    val ageTextState = remember { mutableStateOf("") }

    val step = remember { mutableStateOf(0) }

    val isCareGiverNameValid = careGiverNameState.value.isNotBlank()
    val isPhoneValid = phoneState.value.length == 11 && phoneState.value.all { it.isDigit() }
    val isCareReceiverNameValid = careReceiverNameState.value.isNotBlank()
    val isAgeValid = ageTextState.value.toIntOrNull()?.let { it in 1..120 } ?: false

    val isFilled = isCareGiverNameValid && isPhoneValid && isCareReceiverNameValid && isAgeValid


    val fcmToken = remember { mutableStateOf("") }

    // FCM 토큰을 미리 가져옴
    LaunchedEffect(Unit) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                fcmToken.value = task.result
                Timber.tag("FCM").d("fcm 토큰 screen에서 감지: ${fcmToken.value}")
            } else {
                Timber.tag("FCM").e(task.exception, "FCM 토큰 가져오기 실패")
            }
        }
    }

    LaunchedEffect(careGiverNameState.value) {
        if (isCareGiverNameValid && step.value < 1) step.value = 1
    }

    LaunchedEffect(phoneState.value) {
        if (isPhoneValid && step.value < 2) step.value = 2
    }

    LaunchedEffect(careReceiverNameState.value) {
        if (isCareReceiverNameValid && step.value < 3) step.value = 3
    }

    LaunchedEffect(ageTextState.value) {
        if (isAgeValid && step.value < 4) step.value = 4
    }

    Column(
        modifier
            .fillMaxSize()
            .background(Color.White)
            .imePadding()
    ) {
        TopBar("회원가입")
        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 10.dp)
        ) {
            if (step.value >= 3) {
                TextFieldWithTitle(
                    titleText = "피보호자의 나이를 입력해주세요",
                    hint = "숫자만 입력해주세요",
                    maxLength = 3,
                    textState = ageTextState,
                    isError = ageTextState.value.isNotEmpty() && !isAgeValid,
                    isValid = isAgeValid
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            if (step.value >= 2) {
                TextFieldWithTitle(
                    titleText = "피보호자 성함을 입력해주세요",
                    hint = "성함을 입력해주세요",
                    maxLength = 5,
                    textState = careReceiverNameState,
                    isValid = isCareReceiverNameValid

                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            if (step.value >= 1) {
                TextFieldWithTitle(
                    titleText = "보호자 전화번호를 입력해주세요.",
                    subText = "하이픈(-) 기호는 제외하고 숫자만 입력해주세요.",
                    hint = "전화번호를 입력해주세요",
                    maxLength = 11,
                    textState = phoneState,
                    isError = phoneState.value.isNotEmpty() && !isPhoneValid,
                    isValid = isPhoneValid

                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            if (step.value >= 0) {
                TextFieldWithTitle(
                    titleText = "보호자 성함을 입력해주세요",
                    hint = "성함을 입력해주세요",
                    maxLength = 5,
                    textState = careGiverNameState,
                    isValid = isCareGiverNameValid
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        NextButton(
            text = "다음",
            isEnabled = isFilled,
            onClick = {
                val signUp = SignUp(
                    careGiverName = careGiverNameState.value,
                    careReceiverName = careReceiverNameState.value,
                    careReceiverAge = ageTextState.value.toIntOrNull() ?: 0,
                    careReceiverPhoneNumber = phoneState.value
                )

                viewmodel.signUp(signUp) { response ->
                    fcmViewModel.registerFcmToken(response.id.toLong(), fcmToken.value)

                    navController.navigate(ScreenRoute.SIGN_UP_COMPLETE_SCREEN)

                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .navigationBarsPadding()
        )
    }
}
