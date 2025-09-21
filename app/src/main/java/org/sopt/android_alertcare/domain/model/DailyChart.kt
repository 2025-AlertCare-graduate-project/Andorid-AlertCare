package org.sopt.android_alertcare.domain.model

data class DailyChart(
    val activeTime: Int,
    val dailySummaryText: String,
    val date: String,
    val id: Int,
    val lyingTime: Int,
    val sittingTime: Int,
    val userId: Int
)
