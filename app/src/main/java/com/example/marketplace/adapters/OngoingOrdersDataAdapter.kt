package com.example.marketplace.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marketplace.R
import com.example.marketplace.fragments.OngoingOrdersFragment
import com.example.marketplace.models.Order

class OngoingOrdersDataAdapter (
    private var list: ArrayList<Order>,
    private val context: Context,
    private val listener: OngoingOrdersFragment
) : RecyclerView.Adapter<OngoingOrdersDataAdapter.DataViewHolder>() {

    interface OnItemClickListener{
        fun onDeleteClick(position : Int)
    }



    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.imageView_order_layout)
        val textView_priceAndAmount: TextView = itemView.findViewById(R.id.textView_priceAndAmount_order_layout)
        val textView_productname: TextView = itemView.findViewById(R.id.textView_name_order_layout)
        val textView_name_seller: TextView = itemView.findViewById(R.id.textView_sellername_order_layout)
        val textView_status: TextView = itemView.findViewById(R.id.textView_order_status)
        val textView_delete: TextView = itemView.findViewById(R.id.textView_delete)

        init{
            textView_delete.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val currentPosition = this.adapterPosition

            if (currentPosition != RecyclerView.NO_POSITION) {
                if (this.textView_delete.isPressed) {
                    listener.onDeleteClick(currentPosition)
                }
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_order, parent, false)
        return DataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = list[position]
        holder.textView_name_seller.text = currentItem.owner_username
        holder.textView_priceAndAmount.setText("Amount: "+ currentItem.units + " | Price: " + currentItem.price_per_unit)
        holder.textView_productname.setText(currentItem.title)
        holder.textView_status.setText(currentItem.status)

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