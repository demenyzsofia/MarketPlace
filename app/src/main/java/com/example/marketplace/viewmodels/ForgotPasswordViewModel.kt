package com.example.marketplace.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marketplace.MyApplication
import com.example.marketplace.models.ResetPasswordRequest
import com.example.marketplace.models.User
import com.example.marketplace.repository.Repository
import retrofit2.HttpException

class ForgotPasswordViewModel(val context: Context, val repository: Repository): ViewModel() {
    var token: MutableLiveData<String> = MutableLiveData()
    var user = MutableLiveData<User>()

    init {
        user.value = User()
    }

    suspend fun resetPassword() {
        val request =
            ResetPasswordRequest(username = user.value!!.username, email = user.value!!.email)
        try {
            val result = repository.resetPassword(request)
            MyApplication.token = result.token
            token.value = result.token
            Log.d("xxx", "MyApplication - token:  ${MyApplication.token}")
        } catch (e: HttpException) {
            Log.d("xxx","LoginViewModel - exception: ${e.message()}")
            when(e.code()){
                300 -> Toast.makeText(context , "Username or email not set in body!", Toast.LENGTH_SHORT).show()
                301 -> Toast.makeText(context , "Username or email is wrong!", Toast.LENGTH_SHORT).show()
                302 -> Toast.makeText(context , "Mail could not be sent!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}