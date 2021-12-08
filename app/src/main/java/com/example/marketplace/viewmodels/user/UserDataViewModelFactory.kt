package com.example.marketplace.viewmodels.user

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marketplace.repository.Repository

class UserDataViewModelFactory (private val context: Context,private val repository: Repository,private val username:String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserDataViewModel(context,repository, username) as T
    }
}



