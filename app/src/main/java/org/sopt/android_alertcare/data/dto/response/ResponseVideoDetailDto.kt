package org.sopt.android_alertcare.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseVideoDetailDto(
    @SerialName("videoUrl")
    val videoUrl: String,
    @SerialName("fallDetectTime")
    val fallDetectTime: String,
    @SerialName("careReceiverPhoneNumber")
    val careReceiverPhoneNumber: String,
)
