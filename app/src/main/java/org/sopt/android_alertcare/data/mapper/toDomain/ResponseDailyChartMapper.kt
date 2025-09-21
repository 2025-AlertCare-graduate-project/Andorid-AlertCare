package org.sopt.android_alertcare.data.mapper.toDomain

import org.sopt.android_alertcare.data.dto.response.ResponseDailyChartDto
import org.sopt.android_alertcare.domain.model.DailyChart


fun ResponseDailyChartDto.toDomain(): DailyChart =
    DailyChart(
        activeTime = activeTime,
        dailySummaryText = dailySummaryText,
        date = date,
        id = id,
        lyingTime = lyingTime,
        sittingTime = sittingTime,
        userId = userId
    )
