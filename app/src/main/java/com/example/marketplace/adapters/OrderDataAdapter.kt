package com.example.marketplace.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marketplace.R

import com.example.marketplace.fragments.OrderFragment
import com.example.marketplace.models.Order


class OrderDataAdapter (
    private var list: ArrayList<Order>,
    private val context: Context,
    private val listener: OrderFragment
) :RecyclerView.Adapter<OrderDataAdapter.DataViewHolder>() {



    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.imageView_order_layout)
        val textView_priceAndAmount: TextView = itemView.findViewById(R.id.textView_priceAndAmount_order_layout)
        val textView_productname: TextView = itemView.findViewById(R.id.textView_name_order_layout)
        val textView_name_seller: TextView = itemView.findViewById(R.id.textView_sellername_order_layout)
        val textView_status: TextView = itemView.findViewById(R.id.textView_order_status)
        val textView_delete: TextView = itemView.findViewById(R.id.textView_delete)
        override fun onClick(p0: View?) {

        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_order, parent, false)
        return DataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = list[position]

        if (currentItem.owner_username[0].toString() == "\""){
            holder.textView_name_seller.text = currentItem.owner_username.substring(1,currentItem.owner_username.length-1)
            if (currentItem.units.length <=2 || currentItem.price_per_unit.length <=2 ){
                holder.textView_priceAndAmount.setText("Amount: " + currentItem.units +
                        " | Price: " + currentItem.price_per_unit)
            }
            else {
                holder.textView_priceAndAmount.setText("Amount: " + currentItem.units.substring(1,
                    currentItem.units.length - 1) +
                        " | Price: " + currentItem.price_per_unit.substring(1,
                    currentItem.price_per_unit.length - 1))
            }
            holder.textView_productname.setText(currentItem.title.substring(1,currentItem.title.length-1))
            holder.textView_status.setText(currentItem.status)
        }
        else{
            holder.textView_name_seller.text = currentItem.owner_username
            holder.textView_priceAndAmount.setText("Amount: "+ currentItem.units + " | Price: " + currentItem.price_per_unit)
            holder.textView_productname.setText(currentItem.title)
            holder.textView_status.setText(currentItem.status)
        }
        holder.textView_delete.visibility = View.GONE

        Glide.with(this.context)
            .load(R.drawable.order)
            .override(200, 200)
            .into(holder.imageView);
    }

    override fun getItemCount() = list.size


    fun setData(newlist: ArrayList<Order>){
        list = newlist
    }



}