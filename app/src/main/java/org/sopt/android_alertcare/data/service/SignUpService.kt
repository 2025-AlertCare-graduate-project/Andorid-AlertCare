package org.sopt.android_alertcare.data.service

import org.sopt.android_alertcare.data.dto.request.RequestSignUpDto
import org.sopt.android_alertcare.data.dto.response.BaseResponse
import org.sopt.android_alertcare.data.dto.response.ResponseSignUpDto
import org.sopt.android_alertcare.data.dto.response.ResponseVideoList
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SignUpService {

    @POST("/api/v1/user/signup")
    suspend fun signUp(
        @Body requestSignUpDto: RequestSignUpDto
    ): BaseResponse<ResponseSignUpDto>

    @GET("/api/v1/videos/list/{phoneNumber}")
    suspend fun video(
        @Path("phoneNumber") phoneNumber: String
    ): BaseResponse<List<ResponseVideoList>>


    @GET("/api/v1/videos/{id}")
    suspend fun videoDetail(
        @Path("id") id: Long
    ): BaseResponse<String>
}
