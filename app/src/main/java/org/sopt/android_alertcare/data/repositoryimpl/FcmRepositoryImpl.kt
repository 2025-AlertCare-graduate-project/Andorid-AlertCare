package org.sopt.android_alertcare.data.repositoryimpl

import org.sopt.android_alertcare.data.dto.request.FcmRequestDto
import org.sopt.android_alertcare.data.dto.response.isClientError
import org.sopt.android_alertcare.data.dto.response.isServerError
import org.sopt.android_alertcare.data.dto.response.isSuccess
import org.sopt.android_alertcare.data.service.FcmService
import org.sopt.android_alertcare.domain.repository.FcmRepository

class FcmRepositoryImpl(
    private val service: FcmService
) : FcmRepository {

    override suspend fun sendToken(userId: Long, token: String): Result<Unit> {
        return try {
            val response = service.sendToken(FcmRequestDto(userId, token))

            when {
                response.isSuccess() -> Result.success(Unit)
                response.isClientError() -> Result.failure(Exception("클라이언트 오류: ${response.message}"))
                response.isServerError() -> Result.failure(Exception("서버 오류: ${response.message}"))
                else -> Result.failure(Exception("알 수 없는 오류"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
