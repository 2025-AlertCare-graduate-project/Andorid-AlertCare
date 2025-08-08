package org.sopt.android_alertcare.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseVideoCheckDto(
    @SerialName("id")
    val id: Long,
    @SerialName("checked")
    val checked: Boolean,
)
