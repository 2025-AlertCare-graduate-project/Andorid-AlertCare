package org.sopt.android_alertcare.domain.model

data class SignUpResponse(
    val careGiverName: String,
    val careReceiverAge: Int,
    val careReceiverName: String,
    val careReceiverPhoneNumber: String,
    val fcmToken: String? =null,
    val id: Int
)
