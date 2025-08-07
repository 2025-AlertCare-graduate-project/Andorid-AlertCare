package org.sopt.android_alertcare.domain.repository

import org.sopt.android_alertcare.domain.model.SignUp
import org.sopt.android_alertcare.domain.model.SignUpResponse
import org.sopt.android_alertcare.domain.model.VideoList

interface SignUpRepository {
    suspend fun signUp(signUp: SignUp): Result<SignUpResponse>
    suspend fun videoList(phoneNumber: String): Result<List<VideoList>>
    suspend fun videoDetail(id: Long): Result<String>

}
