package com.example.marketplace.viewmodels.products

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marketplace.MyApplication
import com.example.marketplace.models.AddProduct
import com.example.marketplace.models.AddProductRequest
import com.example.marketplace.repository.Repository
import retrofit2.HttpException

class AddProductViewModel (val context: Context, val repository: Repository): ViewModel() {
    var product = MutableLiveData<AddProduct>()

    init {
        product.value = AddProduct()
    }

    suspend fun addProduct() {
        val request =
            AddProductRequest(title = product.value!!.title, amount_type =product.value!!.amount_type,
            price_type = product.value!!.price_type, description = product.value!!.description,
            is_active = product.value!!.is_active, price_per_unit = product.value!!.price_per_unit,
            units = product.value!!.units,rating = product.value!!.rating)

        try {

            val result = repository.addProduct(MyApplication.token,request)

        } catch (e: HttpException) {
            Log.d("xxx","AddProductViewModel - exception: ${e.message()}")
            when(e.code()){
                300 -> Toast.makeText(context , "Token not sent in header.", Toast.LENGTH_SHORT).show()
                301 -> Toast.makeText(context , "Invalid token", Toast.LENGTH_SHORT).show()
                302 -> Toast.makeText(context , "Token expired. Token must be refreshed.", Toast.LENGTH_SHORT).show()
                303 -> Toast.makeText(context , "Title, description , price or quantity missing.", Toast.LENGTH_SHORT).show()
                304 -> Toast.makeText(context , "Error inserting in database.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}