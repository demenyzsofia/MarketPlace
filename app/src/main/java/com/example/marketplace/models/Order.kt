package com.example.marketplace.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Order (var order_id:String="",var username: String="",
                  var price_per_unit: String="", var units: String="",
                  var description: String="", var title: String="",
                  var status: String = "", var owner_username: String="",
                  var creation_time: Long, var message: String= "")

@JsonClass(generateAdapter = true)
data class OrdersResponse (val item_count: Int, val orders: List<Order>, val timestamp: Long)

data class AddOrder(var price_per_unit: String="", var units: String="",
                    var description: String="", var title: String="",
                    var status: String = "", var owner_username: String="", var revolut_link: String="")

@JsonClass(generateAdapter = true)
data class AddOrderRequest (
    var title: String,
    var description: String,
    var price_per_unit: String,
    var units: String,
    var status: String,
    var owner_username: String,
    var revolut_link: String )

@JsonClass(generateAdapter = true)
data class AddOrderResponse (
    var creation: String,
    var order_id: String,
    var username: String,
    var status: String,
    var price_per_unit: String,
    var units: String,
    var description: String,
    var title: String,
    var creation_time: Long)



@JsonClass(generateAdapter = true)
data class RemoveOrdersResponse(
    var message: String,
    var order_id: String,
    var deletion_time: String,
)