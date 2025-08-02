package org.sopt.android_alertcare.core.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import org.sopt.android_alertcare.data.ServicePool
import org.sopt.android_alertcare.data.repositoryimpl.FcmRepositoryImpl
import org.sopt.android_alertcare.data.repositoryimpl.SignUpRepositoryImpl
import org.sopt.android_alertcare.common.FcmViewModel
import org.sopt.android_alertcare.presentation.signup.SignUpViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

        val savedStateHandle = extras.createSavedStateHandle()

        return when (modelClass) {

            SignUpViewModel::class.java -> {
                SignUpViewModel(
                    SignUpRepositoryImpl(ServicePool.signUpService),
                ) as T
            }

            FcmViewModel::class.java -> {
                FcmViewModel(
                    FcmRepositoryImpl(ServicePool.fcmService),
                ) as T
            }


            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}
