package org.sopt.android_alertcare.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val status: String,
    val code: Int,
    val message: String? = null,
    val data: T? = null,
)

fun <T> BaseResponse<T>.isSuccess(): Boolean =
    code in 200..299

fun <T> BaseResponse<T>.isClientError(): Boolean =
    code in 400..499

fun <T> BaseResponse<T>.isServerError(): Boolean =
    code in 500..599
