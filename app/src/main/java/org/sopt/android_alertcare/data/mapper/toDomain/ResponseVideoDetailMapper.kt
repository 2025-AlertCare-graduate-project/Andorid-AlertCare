package org.sopt.android_alertcare.data.mapper.toDomain

import org.sopt.android_alertcare.data.dto.response.ResponseVideoDetailDto
import org.sopt.android_alertcare.domain.model.VideoDetail

fun ResponseVideoDetailDto.toDomain(): VideoDetail =
    VideoDetail(
        videoUrl = videoUrl,
        fallDetectTime = fallDetectTime,
        careReceiverPhoneNumber = careReceiverPhoneNumber,
    )
