package com.example.marketplace.viewmodels.orders

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marketplace.MyApplication
import com.example.marketplace.models.AddOrder
import com.example.marketplace.models.AddOrderRequest
import com.example.marketplace.repository.Repository
import retrofit2.HttpException

class AddOrderViewModel (val context: Context, val repository: Repository): ViewModel() {
    var order = MutableLiveData<AddOrder>()

    init {
        order.value = AddOrder()
    }

    suspend fun addOrder() {
        try {
            val request =
                AddOrderRequest(
                    title = order.value!!.title,
                    description = order.value!!.description,
                    price_per_unit = order.value!!.price_per_unit,
                    units = order.value!!.units,
                    status = order.value!!.status,
                    owner_username = order.value!!.owner_username,
                    revolut_link = order.value!!.revolut_link
                )


            repository.addOrder(MyApplication.token, request)


        } catch (e: HttpException) {
            Log.d("xxx", "AddProductViewModel - exception: ${e.message()}")
            when (e.code()) {
                300 -> Toast.makeText(context, "Token not sent in header.", Toast.LENGTH_SHORT)
                    .show()
                301 -> Toast.makeText(context, "Invalid token", Toast.LENGTH_SHORT).show()
                302 -> Toast.makeText(
                    context,
                    "Token expired. Token must be refreshed.",
                    Toast.LENGTH_SHORT
                ).show()
                303 -> Toast.makeText(
                    context,
                    "Title, description , price or quantity missing.",
                    Toast.LENGTH_SHORT
                ).show()
                304 -> Toast.makeText(
                    context,
                    "Error inserting in database.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}