package com.example.marketplace.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marketplace.models.RegisterRequest
import com.example.marketplace.models.User
import com.example.marketplace.models.UserUpdateRequest
import com.example.marketplace.repository.Repository
import retrofit2.HttpException

class UserUpdateViewModel(val context: Context, val repository: Repository): ViewModel() {
    var token: MutableLiveData<String> = MutableLiveData()
    var user = MutableLiveData<User>()

    init {
        user.value = User()
    }

    suspend fun userUpdate() {
        val request =
            UserUpdateRequest(username = user.value!!.username, email = user.value!!.email, phone_number = user.value!!.phone_number)

        try {
            val result = repository.userUpdate(request)

        } catch (e: HttpException) {
            Log.d("xxx","UserUpdateViewModel - exception: ${e.message()}")
            when(e.code()){
                300 -> Toast.makeText(context , "Token not sent in header.", Toast.LENGTH_SHORT).show()
                301 -> Toast.makeText(context , "Invalid token", Toast.LENGTH_SHORT).show()
                302 -> Toast.makeText(context , "Token expired.", Toast.LENGTH_SHORT).show()
                303 -> Toast.makeText(context , "Upload image failure", Toast.LENGTH_SHORT).show()
                304 -> Toast.makeText(context , "User not in database", Toast.LENGTH_SHORT).show()
                305 -> Toast.makeText(context , "Token invalid.Please refresh or login again.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}