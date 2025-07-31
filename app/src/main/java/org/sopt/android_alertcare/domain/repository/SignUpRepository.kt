package org.sopt.android_alertcare.domain.repository

import org.sopt.android_alertcare.domain.model.SignUp
import org.sopt.android_alertcare.domain.model.SignUpResponse

interface SignUpRepository {
    suspend fun signUp(signUp: SignUp): Result<SignUpResponse>
}
