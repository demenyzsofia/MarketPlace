package com.example.marketplace.models

import com.squareup.moshi.JsonClass


data class User(var username: String="", var password: String="",
                var email: String="", var phone_number: String="")

@JsonClass(generateAdapter = true)
data class LoginRequest (
    var username: String,
    var password: String
)

@JsonClass(generateAdapter = true)
data class LoginResponse (
    var username: String,
    var email: String,
    var phone_number: String,
    var token: String,
    var creation_time: Long,
    var refresh_time: Long
)


@JsonClass(generateAdapter = true)
data class RegisterRequest (
    var username: String,
    var password: String,
    var email: String,
    var phone_number: String
)

@JsonClass(generateAdapter = true)
data class RegisterResponse (
    var code: Int,
    var message: String,
    var creation_time: Long,

)

@JsonClass(generateAdapter = true)
data class ResetPasswordRequest (
    var username: String,
    var email: String
)

@JsonClass(generateAdapter = true)
data class ResetPasswordResponse (
    var code: Int,
    var message: String,
    var timestamp: Long,

)


@JsonClass(generateAdapter = true)
data class UserUpdateRequest (
    var username: String,
    var email: String,
    var phone_number: String,
//    var image : File

)

@JsonClass(generateAdapter = true)
data class UpdatedData (
    var username : String,
    var phone_number : String,
    var email : String,
    var is_activated: Boolean,
    var creation_time : String,
    var token : String
    )

@JsonClass(generateAdapter = true)
data class UserUpdateResponse (
    var code: Int,
    var updatedData : UpdatedData,
    var timestamp: Long

)

@JsonClass(generateAdapter = true)
data class OneUser(val username: String,
                   val phone_number: Int,
                   val email: String,
                   val is_activated: Boolean,
                   val creation_time: Long)

@JsonClass(generateAdapter = true)
data class UserDataResponse(val code: Int, val data: List<OneUser>, val timestamp: Long)
