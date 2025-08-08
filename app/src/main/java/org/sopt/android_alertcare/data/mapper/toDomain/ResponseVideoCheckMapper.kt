package org.sopt.android_alertcare.data.mapper.toDomain

import org.sopt.android_alertcare.data.dto.response.ResponseVideoCheckDto
import org.sopt.android_alertcare.domain.model.VideoCheck


fun ResponseVideoCheckDto.toDomain(): VideoCheck =
    VideoCheck(
        id = id,
        checked = checked
    )
