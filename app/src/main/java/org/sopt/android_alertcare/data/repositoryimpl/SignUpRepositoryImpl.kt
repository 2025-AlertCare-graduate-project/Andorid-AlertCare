package org.sopt.android_alertcare.data.repositoryimpl

import org.sopt.android_alertcare.data.mapper.toData.toData
import org.sopt.android_alertcare.data.mapper.toDomain.toDomain
import org.sopt.android_alertcare.data.service.SignUpService
import org.sopt.android_alertcare.domain.model.SignUp
import org.sopt.android_alertcare.domain.model.SignUpResponse
import org.sopt.android_alertcare.domain.repository.SignUpRepository


class SignUpRepositoryImpl(
    private val signUpService: SignUpService
) : SignUpRepository {

    override suspend fun signUp(signUp: SignUp): Result<SignUpResponse> = runCatching {
        val requestDto = signUp.toData()
        val response = signUpService.signUp(requestDto)
        response.data?.toDomain()
            ?: throw IllegalStateException("회원가입 응답이 null")
    }
}
