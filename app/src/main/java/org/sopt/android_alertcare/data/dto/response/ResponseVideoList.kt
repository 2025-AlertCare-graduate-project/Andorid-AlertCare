package org.sopt.android_alertcare.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseVideoList(
    @SerialName("id")
    val id: Long,
    @SerialName("fallDetectTime")
    val fallDetectTime: String,
    @SerialName("videoAccessible")
    val videoAccessible: Boolean,
    @SerialName("checkedByUser")
    val checkedByUser: Boolean
)
