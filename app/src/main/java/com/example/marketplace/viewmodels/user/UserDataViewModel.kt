package com.example.marketplace.viewmodels.user

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.MyApplication
import com.example.marketplace.models.OneUser
import com.example.marketplace.models.Product
import com.example.marketplace.models.UserDataResponse
import com.example.marketplace.repository.Repository
import com.squareup.moshi.ToJson
import kotlinx.coroutines.launch


class UserDataViewModel(val context: Context,val repository: Repository, val username: String) : ViewModel() {
    var userData: MutableLiveData<List<OneUser>>  = MutableLiveData()

    init{
        getUserData()
    }

    fun getUserData() {
        viewModelScope.launch {
            try {
                val result =
                    repository.getUserData(username)
                userData.value = result.data
//                Log.d("xxx", "UserDataViewModel - #user:  ${result}")
            } catch (e: Exception) {
                Log.d("xxx", "UserDataViewModel exception: ${e.toString()}")
            }
        }
    }

}