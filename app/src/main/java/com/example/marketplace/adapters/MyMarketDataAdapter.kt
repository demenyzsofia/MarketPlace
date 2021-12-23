package com.example.marketplace.adapters

import android.content.Context
import android.text.TextUtils.substring
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marketplace.R
import com.example.marketplace.fragments.MyMarketFragment
import com.example.marketplace.models.Product

class MyMarketDataAdapter (
    private var list: ArrayList<Product>,
    private val context: Context,
    private val listener: MyMarketFragment,
) :
    RecyclerView.Adapter<MyMarketDataAdapter.DataViewHolder>() {

    interface OnItemClickListener{
        fun onItemClick(position: Int)
        fun onDeleteClick(position : Int)
    }


    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.imageView_product_mymarketitem_layout)
        val textView_price: TextView = itemView.findViewById(R.id.textView_price_mymarketitem_layout)
        val textView_productname: TextView = itemView.findViewById(R.id.textView_name_mymarketitem_layout)
        val textView_name_seller: TextView = itemView.findViewById(R.id.textView_sellername_mymarketitem_layout)
        val textView_isActive: TextView = itemView.findViewById(R.id.textView_isActive_mymarketitem_layout)
        val isActive_image : ImageView = itemView.findViewById(R.id.imageView_isactive_mymarket)
        val delete_button : Button = itemView.findViewById(R.id.button_delete)

        init{
            itemView.setOnClickListener(this)
            textView_name_seller.setOnClickListener(this)
            delete_button.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val currentPosition = this.adapterPosition

            if (currentPosition != RecyclerView.NO_POSITION) {


                if (this.delete_button.isPressed) {
                    listener.onDeleteClick(currentPosition)
                }
                else{
                    listener.onItemClick(currentPosition)
                }
            }

        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_mymarket, parent, false)
        return DataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = list[position]

        if (currentItem.title[0].toString() == "\"") {
            holder.textView_name_seller.text = currentItem.username
            holder.textView_price.setText(currentItem.price_per_unit.substring(1,currentItem.price_per_unit.length-1) + " " +
                    currentItem.amount_type.substring(1,currentItem.amount_type.length-1))
            holder.textView_productname.setText(currentItem.title.substring(1,currentItem.title.length-1))
        }
        else{
            holder.textView_name_seller.text = currentItem.username
            holder.textView_price.setText(currentItem.price_per_unit + " " + currentItem.amount_type)
            holder.textView_productname.setText(currentItem.title)
        }



        if(currentItem.is_active.toString() == "false"){
            holder.isActive_image.setVisibility(View.GONE)
            holder.textView_isActive.setText("Inactive")
        }
        else {
            holder.isActive_image.setVisibility(View.VISIBLE)
            holder.textView_isActive.setText("Active")
        }
        val images = currentItem.images
        if( images != null && images.size > 0) {
            Log.d("xxx", "#num_images: ${images.size}")
        }
        Glide.with(this.context)
            .load(R.drawable.product)
            .override(200, 200)
            .into(holder.imageView);
    }

    override fun getItemCount() = list.size


    fun setData(newlist: ArrayList<Product>){
        list = newlist
    }



}