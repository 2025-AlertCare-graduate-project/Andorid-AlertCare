package org.sopt.android_alertcare.data.service

import org.sopt.android_alertcare.data.dto.request.FcmRequestDto
import org.sopt.android_alertcare.data.dto.response.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface FcmService {

        @POST("api/fcm/register")
        suspend fun sendToken(@Body request: FcmRequestDto): BaseResponse<Unit>
}
