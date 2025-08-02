package org.sopt.android_alertcare.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.android_alertcare.domain.model.SignUp
import org.sopt.android_alertcare.domain.model.SignUpResponse
import org.sopt.android_alertcare.domain.repository.SignUpRepository
import org.sopt.android_alertcare.presentation.util.UiState


class SignUpViewModel(
    private val signUpRepository: SignUpRepository
) : ViewModel() {

    private val _signUpState = MutableStateFlow<UiState<SignUpResponse>>(UiState.Empty)
    val signUpState: StateFlow<UiState<SignUpResponse>> = _signUpState

    fun signUp(signUp: SignUp, onSuccess: (userId: Int) -> Unit = {}) {
        viewModelScope.launch {
            _signUpState.value = UiState.Loading

            val result = signUpRepository.signUp(signUp)

            _signUpState.value = result.fold(
                onSuccess = {
                    onSuccess(it.id)  // userId 콜백
                    UiState.Success(it)
                },
                onFailure = {
                    UiState.Error(it.message ?: "오류 발생")
                }
            )
        }
    }

}
