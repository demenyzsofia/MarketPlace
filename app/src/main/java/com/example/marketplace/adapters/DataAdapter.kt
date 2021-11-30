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
import com.example.marketplace.models.Product

class DataAdapter(
    private var list: ArrayList<Product>,
    private val context: Context,
    private val listener: OnItemClickListener,
    private val listener2: OnItemLongClickListener
) :
    RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    interface OnItemClickListener{
        fun onSellerNameClick(position : Int)
        fun onItemClick(position: Int)

    }

    interface OnItemLongClickListener{
        fun onItemLongClick(position: Int)
    }

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener, View.OnLongClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.imageView_product_item_layout)
        val textView_price: TextView = itemView.findViewById(R.id.textView_price_item_layout)
        val textView_productname: TextView = itemView.findViewById(R.id.textView_name_item_layout)
        val textView_name_seller: TextView = itemView.findViewById(R.id.textView_sellername_item_layout)



        init{
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
            textView_name_seller.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val currentPosition = this.adapterPosition


            if (this.textView_name_seller.isPressed) {
                    listener.onSellerNameClick(currentPosition)

            }
            else{
                listener.onItemClick(currentPosition)
            }

        }

        override fun onLongClick(p0: View?): Boolean {
            val currentPosition = this.adapterPosition
            listener2.onItemLongClick(currentPosition)
            return true
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return DataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = list[position]
        holder.textView_name_seller.text = currentItem.username
        holder.textView_price.text = currentItem.price_per_unit
        holder.textView_productname.text = currentItem.title
        val images = currentItem.images
        if( images != null && images.size > 0) {
            Log.d("xxx", "#num_images: ${images.size}")
        }
        Glide.with(this.context)
            .load(R.mipmap.shopping_icon_foreground)
            .override(200, 200)
            .into(holder.imageView);
    }

    override fun getItemCount() = list.size


    fun setData(newlist: ArrayList<Product>){
        list = newlist
    }



}