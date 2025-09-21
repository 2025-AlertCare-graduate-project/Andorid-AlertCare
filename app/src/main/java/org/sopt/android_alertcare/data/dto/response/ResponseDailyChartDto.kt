package org.sopt.android_alertcare.data.dto.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDailyChartDto(
    @SerialName("activeTime")
    val activeTime: Int,
    @SerialName("dailySummaryText")
    val dailySummaryText: String,
    @SerialName("date")
    val date: String,
    @SerialName("id")
    val id: Int,
    @SerialName("lyingTime")
    val lyingTime: Int,
    @SerialName("sittingTime")
    val sittingTime: Int,
    @SerialName("userId")
    val userId: Int
)
