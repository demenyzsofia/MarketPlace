package com.example.marketplace.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marketplace.MyApplication
import com.example.marketplace.models.RegisterRequest
import com.example.marketplace.models.User
import com.example.marketplace.repository.Repository
import retrofit2.HttpException


class RegisterViewModel(val context: Context, val repository: Repository): ViewModel() {
    var token: MutableLiveData<String> = MutableLiveData()
    var user = MutableLiveData<User>()

    init {
        user.value = User()
    }

    suspend fun register() {
        val request =
            RegisterRequest(username = user.value!!.username, password = user.value!!.password, email = user.value!!.email, phone_number = user.value!!.phone_number)
        try {
            val result = repository.register(request)
            MyApplication.token = result.token
            token.value = result.token
            Log.d("xxx", "MyApplication - token:  ${MyApplication.token}")
        } catch (e: HttpException) {
            Log.d("xxx","RegisterViewModel - exception: ${e.message()}")
            when(e.code()){
                300 -> Toast.makeText(context , "One of the following username , password ,\n" +
                        "email , phone_number, userImage are either\n" +
                        "empty or missing!", Toast.LENGTH_SHORT).show()
                301 -> Toast.makeText(context , "Wrong file format. Only jpeg or png are\n" +
                        "allowed.\n", Toast.LENGTH_SHORT).show()
                302 -> Toast.makeText(context , "Email incorrect. You need to enter another\n" +
                        "email.\n", Toast.LENGTH_SHORT).show()
                303 -> Toast.makeText(context , "Username , email or phone number already\n" +
                        "used.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}