package com.example.marketplace.viewmodels

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


class UserDataViewModel(val repository: Repository): ViewModel() {
    var userData: MutableLiveData<List<OneUser>>  = MutableLiveData()

    init{
        getUserData()
    }

    fun getUserData() {
        viewModelScope.launch {
            try {
                val result =
                    repository.getUserData("teszt12")
                userData.value = result.data
                Log.i("itttt",userData.value?.get(0)?.email.toString())
                Log.d("xxx", "UserDataViewModel - #user:  ${result}")
            } catch (e: Exception) {
                Log.d("xxx", "UserDataViewModel exception: ${e.toString()}")
            }
        }
    }

}