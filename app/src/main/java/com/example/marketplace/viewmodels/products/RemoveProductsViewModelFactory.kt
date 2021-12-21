package com.example.marketplace.viewmodels.products

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marketplace.repository.Repository

class RemoveProductsViewModelFactory (private val context: Context, private val repository: Repository,
private val prod_id:String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RemoveProductsViewModel(context,repository,prod_id) as T
    }
}