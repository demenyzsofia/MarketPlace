package com.example.marketplace.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.marketplace.R
import com.example.marketplace.models.Product
import com.example.marketplace.repository.Repository
import com.example.marketplace.viewmodels.products.ListViewModel
import com.example.marketplace.viewmodels.products.ListViewModelFactory


class MyProductDetailFragment : Fragment() {
    private lateinit var productName : TextView
    private lateinit var productSeller : TextView
    private lateinit var productDescription : TextView
    private lateinit var productPrice : TextView
    private lateinit var isActive : TextView
    private lateinit var totalItems : TextView
    private lateinit var priceItem: TextView
    private lateinit var selledItems: TextView
    private lateinit var revenew: TextView

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
        val view = inflater.inflate(R.layout.fragment_my_product_detail, container, false)
        view?.apply {
            initializeView(this)
            registerListeners(this)
        }

        return view
    }

    private fun registerListeners(view: View) {
        val product: Product? = listViewModel.getOneProductMyList()

        Log.i("itttt2",product.toString())
        productDescription.setText(product?.description.toString())
        productPrice.setText(product?.price_per_unit.toString() + " " + product?.price_type.toString() + "/" + product?.amount_type.toString())
        productSeller.setText(product?.username.toString())
        productName.setText(product?.title.toString())
        if(product?.is_active.toString() == "true"){
            isActive.text = "Active"
        }
        else{
            isActive.text = "Inactive"
        }
        totalItems.text = product?.units.toString() + " " + product?.amount_type.toString()
        priceItem.text = product?.price_per_unit.toString() + " " + product?.price_type.toString()
        selledItems.text = "0" + " " + product?.amount_type.toString()
        revenew.text = "0" + product?.price_type.toString()

    }

    private fun initializeView(view: View) {
        productName = view.findViewById(R.id.textView_myProduct_name)
        productSeller = view.findViewById(R.id.textView_myProduct_seller)
        productPrice = view.findViewById(R.id.textView_myProduct_price)
        productDescription = view.findViewById(R.id.textView_myProduct_description)
        isActive = view.findViewById(R.id.textView_myProduct_isActive)
        totalItems = view.findViewById(R.id.textView_myProduct_totalItems)
        priceItem = view.findViewById(R.id.textView_myProduct_price_item)
        selledItems = view.findViewById(R.id.textView_myProduct_selledItems)
        revenew = view.findViewById(R.id.textView_myProduct_revenew)

    }


}