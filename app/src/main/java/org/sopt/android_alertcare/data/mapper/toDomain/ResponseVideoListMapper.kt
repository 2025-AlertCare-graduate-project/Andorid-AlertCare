package org.sopt.android_alertcare.data.mapper.toDomain

import org.sopt.android_alertcare.data.dto.response.ResponseVideoList
import org.sopt.android_alertcare.domain.model.VideoList

fun ResponseVideoList.toDomain(): VideoList =
    VideoList(
        id = id,
        fallDetectTime = fallDetectTime,
        videoAccessible = videoAccessible,
        checkedByUser = checkedByUser
    )

fun List<ResponseVideoList>.toDomain(): List<VideoList> =
    map { it.toDomain() }
