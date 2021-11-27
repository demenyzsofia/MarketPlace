package com.example.marketplace.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.json.JSONArray
import java.io.File

data class User(var username: String="", var password: String="", var email: String="", var phone_number: String="")

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


)

@JsonClass(generateAdapter = true)
data class UserUpdateResponse (
    var code: Int,
    var updateData : JSONArray,
    var timestamp: Long

)
