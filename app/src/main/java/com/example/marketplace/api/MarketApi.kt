package com.example.marketplace.api

import com.example.marketplace.models.*
import com.example.marketplace.utils.Constants
import retrofit2.http.*

interface MarketApi {
    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST(Constants.REGISTER_URL)
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    @POST(Constants.RESET_PASSWORD_URL)
    suspend fun resetPassword(@Body request: ResetPasswordRequest): ResetPasswordResponse

    //profil -> settings fragment
    @POST(Constants.USER_UPDATE_URL)
    suspend fun userUpdate(@Body request: UserUpdateRequest): UserUpdateResponse


    @GET(Constants.GET_PRODUCT_URL)
    suspend fun getProducts(@Header("token") token: String): ProductResponse
    //get user
    @GET(Constants.GET_USER_DATA_URL)
    suspend fun getUserData(@Header("username") username: String): UserDataResponse


}