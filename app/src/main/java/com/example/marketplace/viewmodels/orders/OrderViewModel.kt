package com.example.marketplace.viewmodels.orders

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.MyApplication
import com.example.marketplace.models.Order
import com.example.marketplace.repository.Repository
import kotlinx.coroutines.launch

class OrderViewModel (private val repository: Repository) : ViewModel() {
    var orders: MutableLiveData<List<Order>> = MutableLiveData()
    private lateinit var myOrders: ArrayList<Order>

    init{
        getOrders()
    }

    fun updateMyOrders(list : ArrayList<Order>){
        myOrders = list
    }

    fun getOrders() {
        viewModelScope.launch {
            try {
                val result =
                    repository.getOrders(MyApplication.token)
                orders.value = result.orders
                Log.d("xxx", "OrderViewModel - #orders:  ${result.item_count}")
            }catch(e: Exception){
                Log.d("xxx", "OrderViewModel exception: ${e.toString()}")
            }
        }
    }

}