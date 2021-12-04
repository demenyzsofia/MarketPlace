package com.example.marketplace.viewmodels

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marketplace.MyApplication
import com.example.marketplace.models.LoginRequest
import com.example.marketplace.models.User
import com.example.marketplace.repository.Repository
import retrofit2.HttpException
import android.app.Application



class LoginViewModel(val context: Context, val repository: Repository) : ViewModel() {
    var token: MutableLiveData<String> = MutableLiveData()
    var user = MutableLiveData<User>()

    init {
        user.value = User()
    }


    suspend fun login() {
        val request =
            LoginRequest(username = user.value!!.username, password = user.value!!.password)
        try {
            val result = repository.login(request)
            MyApplication.token = result.token
            token.value = result.token
            Log.d("xxx", "MyApplication - token:  ${MyApplication.token}")
        } catch (e: HttpException) {
            Log.d("xxx","LoginViewModel - exception: ${e.message()}")
            when(e.code()){
                    300 -> Toast.makeText(context , "Username or password missing!", Toast.LENGTH_SHORT).show()
                    301 -> Toast.makeText(context , "Account not activated!", Toast.LENGTH_SHORT).show()
                    302 -> Toast.makeText(context , "No such user!", Toast.LENGTH_SHORT).show()
            }
        }
    }




}
