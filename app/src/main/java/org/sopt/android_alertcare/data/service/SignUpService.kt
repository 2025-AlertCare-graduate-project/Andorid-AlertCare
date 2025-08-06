package org.sopt.android_alertcare.data.service

import org.sopt.android_alertcare.data.dto.request.RequestProfileDetailDto
import org.sopt.android_alertcare.data.dto.request.RequestSignUpDto
import org.sopt.android_alertcare.data.dto.response.BaseResponse
import org.sopt.android_alertcare.data.dto.response.ResponseProfileDetailDto
import org.sopt.android_alertcare.data.dto.response.ResponseSignUpDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SignUpService {

    @POST("/api/v1/user/signup")
    suspend fun signUp(
        @Body requestSignUpDto: RequestSignUpDto
    ): BaseResponse<ResponseSignUpDto>


    @GET("/api/v1/user/profile")
    suspend fun profileDetail(
        @Body profileDetailDto: RequestProfileDetailDto
    ): BaseResponse<ResponseProfileDetailDto>
}
