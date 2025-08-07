package org.sopt.android_alertcare.data.dto.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestProfileDetailDto(
    @SerialName("careReceiverPhoneNumber")
    val careReceiverPhoneNumber: String
)
