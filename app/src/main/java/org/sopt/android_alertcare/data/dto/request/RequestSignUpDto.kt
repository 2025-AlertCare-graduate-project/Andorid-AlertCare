package org.sopt.android_alertcare.data.dto.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignUpDto(
    @SerialName("careGiverName")
    val careGiverName: String,
    @SerialName("careReceiverAge")
    val careReceiverAge: Int,
    @SerialName("careReceiverName")
    val careReceiverName: String,
    @SerialName("careReceiverPhoneNumber")
    val careReceiverPhoneNumber: String
)
