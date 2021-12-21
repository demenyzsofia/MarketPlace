package com.example.marketplace.repository

import com.example.marketplace.api.RetrofitInstance
import com.example.marketplace.models.*
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Query

class Repository {
    suspend fun login(request: LoginRequest): LoginResponse {
        return RetrofitInstance.api.login(request)
    }

    suspend fun register(request: RegisterRequest): RegisterResponse {
        return RetrofitInstance.api.register(request)
    }

    suspend fun resetPassword(request: ResetPasswordRequest): ResetPasswordResponse {
        return RetrofitInstance.api.resetPassword(request)
    }

    suspend fun userUpdate(token: String, request: UserUpdateRequest): UserUpdateResponse {
        return RetrofitInstance.api.userUpdate(token,request)
    }

    suspend fun getProducts(token: String, limit : Int): ProductResponse {
        return RetrofitInstance.api.getProducts(token, limit )
    }

    suspend fun getUserData(username: String): UserDataResponse {
        return RetrofitInstance.api.getUserData(username)
    }

    suspend fun addProduct(token: String, request: AddProductRequest): AddProductResponse{
        return RetrofitInstance.api.addProduct(token,
            request.title,
            request.description,
            request.price_per_unit,
            request.units,
            request.is_active,
            request.rating,
            request.amount_type,
            request.price_type
        )
    }
    suspend fun removeProducts(token: String, product_id : String) : RemoveProdutsResponse{
        return RetrofitInstance.api.removeProducts(token,product_id)
    }

    suspend fun addOrder(token: String, request: AddOrderRequest): AddOrderResponse {
        return RetrofitInstance.api.addOrder(token,request)
    }

    suspend fun getOrders( token: String): OrdersResponse{
        return RetrofitInstance.api.getOrders(token)
    }
}