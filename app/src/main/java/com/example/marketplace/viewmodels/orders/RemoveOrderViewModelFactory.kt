package com.example.marketplace.viewmodels.orders

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marketplace.repository.Repository

class RemoveOrderViewModelFactory (private val context: Context, private val repository: Repository,
                                   private val ord_id:String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RemoveOrderViewModel(context,repository,ord_id) as T
    }
}