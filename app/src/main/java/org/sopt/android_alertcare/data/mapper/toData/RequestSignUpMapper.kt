package org.sopt.android_alertcare.data.mapper.toData

import org.sopt.android_alertcare.data.dto.request.RequestSignUpDto
import org.sopt.android_alertcare.domain.model.SignUp

fun SignUp.toData(): RequestSignUpDto =
    RequestSignUpDto(
        careGiverName = careGiverName,
        careReceiverAge = careReceiverAge,
        careReceiverName = careReceiverName,
        careReceiverPhoneNumber = careReceiverPhoneNumber
    )
