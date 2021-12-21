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
    suspend fun userUpdate(@Header("token") token: String, @Body request: UserUpdateRequest): UserUpdateResponse

    @Multipart
    @POST(Constants.ADD_PRODUCT_URL)
    suspend fun addProduct(@Header("token") token: String,
                           @Part("title") title : String,
                           @Part("description") description : String,
                           @Part("price_per_unit") price_per_unit : String,
                           @Part("units") units : String,
                           @Part("is_active") is_active : Boolean,
                           @Part("rating") rating : Double,
                           @Part("amount_type") amount_type : String,
                           @Part("price_type") price_type : String ):AddProductResponse

    @POST(Constants.ADD_ORDERS_URL)
    suspend fun addOrder(@Header("token") token: String, @Body request: AddOrderRequest): AddOrderResponse


    @POST(Constants.REMOVE_PRODUCT_URL)
    suspend fun removeProducts(@Header("token") token: String, @Query("product_id") product_id: String): RemoveProdutsResponse

    @GET(Constants.GET_PRODUCT_URL)
    suspend fun getProducts(@Header("token") token: String,@Header("limit") limit: Int): ProductResponse
    //get user
    @GET(Constants.GET_USER_DATA_URL)
    suspend fun getUserData(@Header("username") username: String): UserDataResponse


}