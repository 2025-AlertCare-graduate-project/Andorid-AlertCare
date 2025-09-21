package org.sopt.android_alertcare.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.android_alertcare.domain.model.DailyChart
import org.sopt.android_alertcare.domain.model.LogIn
import org.sopt.android_alertcare.domain.model.SignUp
import org.sopt.android_alertcare.domain.model.SignUpResponse
import org.sopt.android_alertcare.domain.model.VideoCheck
import org.sopt.android_alertcare.domain.model.VideoDetail
import org.sopt.android_alertcare.domain.model.VideoList
import org.sopt.android_alertcare.domain.repository.SignUpRepository
import org.sopt.android_alertcare.presentation.util.UiState
import timber.log.Timber
import java.time.LocalDate


class SignUpViewModel(
    private val signUpRepository: SignUpRepository
) : ViewModel() {

    private val _signUpState = MutableStateFlow<UiState<SignUpResponse>>(UiState.Empty)
    val signUpState: StateFlow<UiState<SignUpResponse>> = _signUpState

    private val _videoListState = MutableStateFlow<UiState<List<VideoList>>>(UiState.Empty)
    val videoListState: StateFlow<UiState<List<VideoList>>> = _videoListState


    private val _videoUrlState = MutableStateFlow<UiState<VideoDetail>>(UiState.Empty)
    val videoUrlState: StateFlow<UiState<VideoDetail>> = _videoUrlState

    private val _loginState = MutableStateFlow<UiState<SignUpResponse>>(UiState.Empty)
    val loginState: StateFlow<UiState<SignUpResponse>> = _loginState

    private val _videoCheckedState = MutableStateFlow<UiState<VideoCheck>>(UiState.Empty)
    val videoCheckedState: StateFlow<UiState<VideoCheck>> = _videoCheckedState

    private val _dailyChartState = MutableStateFlow<UiState<DailyChart>>(UiState.Empty)
    val dailyChartState: StateFlow<UiState<DailyChart>> = _dailyChartState

    fun signUp(signUp: SignUp, onSuccess: (SignUpResponse) -> Unit = {}) {
        viewModelScope.launch {
            _signUpState.value = UiState.Loading

            val result = signUpRepository.signUp(signUp)

            _signUpState.value = result.fold(
                onSuccess = {
                    Timber.d("회원가입 성공: ${it}")
                    onSuccess(it)
                    UiState.Success(it)
                },
                onFailure = {
                    Timber.e("회원가입 실패: ${it.message}")
                    UiState.Error(it.message ?: "오류 발생")
                }
            )
        }
    }


    fun fetchVideoList(phoneNumber: String) {
        viewModelScope.launch {
            _videoListState.value = UiState.Loading

            val result = signUpRepository.videoList(phoneNumber)

            _videoListState.value = result.fold(
                onSuccess = {
                    Timber.d("영상 리스트 불러오기 성공: $it")
//                    UiState.Success(it)
                    UiState.Success(it.sortedByDescending { v -> v.id })
                },
                onFailure = {
                    Timber.e("영상 리스트 불러오기 실패: ${it.message}")
                    UiState.Error(it.message ?: "영상 데이터를 불러오는 중 오류 발생")
                }
            )
        }
    }

    fun fetchVideoUrl(videoId: Long) {
        viewModelScope.launch {
            _videoUrlState.value = UiState.Loading

            val result = signUpRepository.videoDetail(videoId)

            _videoUrlState.value = result.fold(
                onSuccess = {
                    Timber.d("영상 URL 불러오기 성공: $it")
                    UiState.Success(it)
                },
                onFailure = {
                    Timber.e("영상 URL 불러오기 실패: ${it.message}")
                    UiState.Error(it.message ?: "영상 URL을 불러오는 중 오류 발생")
                }
            )
        }
    }

    fun logIn(logIn: LogIn, onSuccess: (SignUpResponse) -> Unit = {}) {
        viewModelScope.launch {
            _signUpState.value = UiState.Loading

            val result = signUpRepository.logIn(logIn)

            _signUpState.value = result.fold(
                onSuccess = {
                    Timber.d("로그인 성공: ${it}")
                    onSuccess(it)
                    UiState.Success(it)
                },
                onFailure = {
                    Timber.e("로그인 실패: ${it.message}")
                    UiState.Error(it.message ?: "오류 발생")
                }
            )
        }
    }

    fun patchVideoChecked(videoId: Long) {
        viewModelScope.launch {
            _videoCheckedState.value = UiState.Loading

            val result = signUpRepository.videoChecked(videoId)

            _videoCheckedState.value = result.fold(
                onSuccess = {
                    UiState.Success(it)
                },
                onFailure = {
                    UiState.Error(it.message ?: "영상 확인 실패")
                }
            )
        }
    }

    fun getDailyChart(phoneNumber: String, date: LocalDate) {
        viewModelScope.launch {
            _dailyChartState.value = UiState.Loading

            val result = signUpRepository.dailyChart(phoneNumber, date)

            _dailyChartState.value = result.fold(
                onSuccess = {
                    Timber.d("daily chart 불러오기 성공: $it")
                    UiState.Success(it)
                },
                onFailure = {
                    Timber.e("daily chart 불러오기 실패: ${it.message}")
                    UiState.Error(it.message ?: "daily chart 불러오는 중 오류 발생")

                }
            )
        }
    }

}
