package com.example.marketplace.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
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
        val price_per_unit = product?.price_per_unit.toString().substring(1,product?.price_per_unit.toString().length-1)
        val price_type = product?.price_type.toString().substring(1,product?.price_type.toString().length-1)
        val amount_type = product?.amount_type.toString().substring(1,product?.amount_type.toString().length-1)
        val title = product?.title.toString().substring(1,product?.title.toString().length-1)
        val units = product?.units.toString().substring(1,product?.units.toString().length-1)
        val description = product?.description.toString().substring(1,product?.description.toString().length-1)
        productDescription.setText(description)
        productPrice.setText(price_per_unit + " " + price_type + "/" + amount_type)
        productSeller.setText(product?.username.toString())
        productName.setText(title)
        if(product?.is_active.toString() == "true"){
            isActive.text = "Active"
        }
        else{
            isActive.text = "Inactive"
        }
        totalItems.text = units + " " + amount_type
        priceItem.text = price_per_unit + " " + price_type
        selledItems.text = "0" + " " + amount_type
        revenew.text = "0" + price_type

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