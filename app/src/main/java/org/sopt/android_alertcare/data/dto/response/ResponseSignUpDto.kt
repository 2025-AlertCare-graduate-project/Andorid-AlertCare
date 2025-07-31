package org.sopt.android_alertcare.data.dto.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSignUpDto(
    @SerialName("careGiverName")
    val careGiverName: String,
    @SerialName("careReceiverAge")
    val careReceiverAge: Int,
    @SerialName("careReceiverName")
    val careReceiverName: String,
    @SerialName("careReceiverPhoneNumber")
    val careReceiverPhoneNumber: String,
    @SerialName("fcmToken")
    val fcmToken: String,
    @SerialName("id")
    val id: Int
)
