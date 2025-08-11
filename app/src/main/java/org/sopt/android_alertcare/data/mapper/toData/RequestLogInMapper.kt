package org.sopt.android_alertcare.data.mapper.toData

import org.sopt.android_alertcare.data.dto.request.RequestLoginDto
import org.sopt.android_alertcare.domain.model.LogIn

fun LogIn.toData(): RequestLoginDto =
    RequestLoginDto(
        careGiverName = careGiverName,
        careReceiverName = careReceiverName,
        careReceiverPhoneNumber = careReceiverPhoneNumber
    )
