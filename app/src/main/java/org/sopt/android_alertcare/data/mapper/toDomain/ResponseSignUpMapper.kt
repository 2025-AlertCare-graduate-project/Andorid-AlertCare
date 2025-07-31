package org.sopt.android_alertcare.data.mapper.toDomain

import org.sopt.android_alertcare.data.dto.response.ResponseSignUpDto
import org.sopt.android_alertcare.domain.model.SignUpResponse

fun ResponseSignUpDto.toDomain(): SignUpResponse =
    SignUpResponse(
        careGiverName = careGiverName,
        careReceiverAge = careReceiverAge,
        careReceiverName = careReceiverName,
        careReceiverPhoneNumber = careReceiverPhoneNumber,
        fcmToken = fcmToken,
        id = id
    )
