package org.sopt.android_alertcare.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.android_alertcare.domain.repository.FcmRepository
import timber.log.Timber

class FcmViewModel(
    private val repository: FcmRepository
) : ViewModel() {

    fun registerFcmToken(userId: Long, token: String) {
        viewModelScope.launch {
            val result = repository.sendToken(userId, token)

            result.onSuccess {
                Timber.d("✅ FCM 토큰 등록 성공")
            }.onFailure {
                Timber.e(it, "❌ FCM 토큰 등록 실패")
            }
        }
    }
}
