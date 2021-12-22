package com.example.marketplace.viewmodels.orders

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.MyApplication
import com.example.marketplace.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RemoveOrderViewModel (val context: Context, val repository: Repository, val ord_id: String): ViewModel() {
    init {
        revomeOrder()
    }

    fun revomeOrder() {
        viewModelScope.launch {
            try {
                val result =
                    repository.removeOrders(MyApplication.token, ord_id)

                Log.d("xxx", "RemoveOrderViewModel - #result:  ${result}")
            } catch (e: HttpException) {
                Log.d("xxx", "RemoveOrderViewModel - exception: ${e.message()}")
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
                        "Order id header not sent.",
                        Toast.LENGTH_SHORT
                    ).show()
                    305 -> Toast.makeText(
                        context,
                        "Order not in database.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}