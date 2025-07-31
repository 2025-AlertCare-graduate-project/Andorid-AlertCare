package org.sopt.android_alertcare.data.service

import org.sopt.android_alertcare.data.dto.request.RequestSignUpDto
import org.sopt.android_alertcare.data.dto.response.BaseResponse
import org.sopt.android_alertcare.data.dto.response.ResponseSignUpDto
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {

    @POST("/api/v1/user/signup")
    suspend fun signUp(
        @Body requestSignUpDto: RequestSignUpDto
    ): BaseResponse<ResponseSignUpDto>
}
