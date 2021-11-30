package com.example.marketplace.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.marketplace.R
import com.example.marketplace.models.Product
import com.example.marketplace.repository.Repository
import com.example.marketplace.viewmodels.ListViewModel
import com.example.marketplace.viewmodels.ListViewModelFactory
import com.example.marketplace.viewmodels.MarketViewModel

class ProductDetailFragment : Fragment() {
    lateinit var productImage : ImageView
    lateinit var productName : TextView
    lateinit var productSeller : TextView
    lateinit var productDescription : TextView
    lateinit var productPrice : TextView

    val factory = ListViewModelFactory(Repository())
    private val listViewModel: ListViewModel by lazy{
        ViewModelProvider(requireActivity(),factory).get((ListViewModel::class.java))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_detail, container, false)
        view?.apply {
            initializeView(this)
            registerListeners(this)
        }

        return view
    }

    private fun registerListeners(view: View) {
        val product: Product? = listViewModel.getOneProduct()

        productDescription.setText(product?.description)
        productPrice.setText(product?.price_per_unit + " RON")
        productSeller.setText(product?.username)
        productName.setText(product?.title)
        //productImage.setImageResource(product.images)



    }

    private fun initializeView(view: View) {
        productName = view.findViewById(R.id.textView_product_name)
        productSeller = view.findViewById(R.id.textView_product_seller)
        productPrice = view.findViewById(R.id.textView_product_price)
        productDescription = view.findViewById(R.id.textView_product_description)
        productImage = view.findViewById(R.id.imageView_product)
    }

}

