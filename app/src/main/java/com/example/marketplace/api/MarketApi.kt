package com.example.marketplace.api

import com.example.marketplace.models.*
import com.example.marketplace.utils.Constants
import retrofit2.Response
import retrofit2.http.*

interface MarketApi {
    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET(Constants.GET_PRODUCT_URL)
    suspend fun getProducts(@Header("token") token: String): ProductResponse

    @POST(Constants.REGISTER_URL)
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

//    @GET(Constants.ACTIVATE_URL)
//    suspend fun getActivate(@Body request: RegisterRequest): RegisterResponse

}