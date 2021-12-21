package com.example.marketplace.viewmodels.products

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.MyApplication
import com.example.marketplace.models.Product
import com.example.marketplace.repository.Repository
import kotlinx.coroutines.launch

class ListViewModel(private val repository: Repository) : ViewModel() {
    var products: MutableLiveData<List<Product>> = MutableLiveData()
    private lateinit var myProducts: ArrayList<Product>
    private var currentPosition: Int = 0


    init{
        getProducts()
    }

    fun updateMyProducts(list : ArrayList<Product>){
        myProducts = list
    }

    fun getOneProduct(): Product? {
        return products.value?.get(currentPosition)
    }

    fun getOneProductMyList(): Product? {
        return myProducts.get(currentPosition)
    }


    fun getSellerName() : String{
        return products.value?.get(currentPosition)?.username.toString()
    }

    fun getProductId():String{
        return myProducts?.get(currentPosition)?.product_id.toString()
    }

    fun getProducts() {
        viewModelScope.launch {
            try {
                val result =
                    repository.getProducts(MyApplication.token,1000)
                products.value = result.products
                Log.d("xxx", "ListViewModel - #products:  ${result.item_count}")
            }catch(e: Exception){
                Log.d("xxx", "ListViewMofdel exception: ${e.toString()}")
            }
        }
    }

    fun updateCurrentPosition(position : Int): Int {
        currentPosition = position
        return currentPosition
    }
}