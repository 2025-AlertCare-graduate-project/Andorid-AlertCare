package org.sopt.android_alertcare.data.repositoryimpl

import org.sopt.android_alertcare.data.mapper.toData.toData
import org.sopt.android_alertcare.data.mapper.toDomain.toDomain
import org.sopt.android_alertcare.data.service.SignUpService
import org.sopt.android_alertcare.domain.model.SignUp
import org.sopt.android_alertcare.domain.model.SignUpResponse
import org.sopt.android_alertcare.domain.model.VideoList
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


    override suspend fun videoList(phoneNumber: String): Result<List<VideoList>> = runCatching {
        val response = signUpService.video(phoneNumber)
        response.data?.toDomain()
            ?: throw IllegalStateException("영상 리스트 응답이 null")
    }

    override suspend fun videoDetail(id: Long): Result<String> = runCatching {
        val response = signUpService.videoDetail(id)
        response.data ?: throw IllegalStateException("영상 상세 응답이 null")
    }


}
