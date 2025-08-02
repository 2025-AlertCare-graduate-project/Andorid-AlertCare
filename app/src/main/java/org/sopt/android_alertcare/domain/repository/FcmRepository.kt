package org.sopt.android_alertcare.domain.repository

interface FcmRepository {
    suspend fun sendToken(userId: Long, token: String): Result<Unit>
}
