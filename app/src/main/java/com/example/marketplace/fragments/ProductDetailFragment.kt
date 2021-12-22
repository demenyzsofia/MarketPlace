package com.example.marketplace.fragments

import android.os.Bundle
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

class ProductDetailFragment : Fragment() {
    private lateinit var productImage : ImageView
    private lateinit var productName : TextView
    private lateinit var productSeller : TextView
    private lateinit var productDescription : TextView
    private lateinit var productPrice : TextView
    private lateinit var isActive : TextView
    private lateinit var availableAmount : TextView
    private lateinit var orderButton: Button
    private lateinit var emailButton: Button
    private lateinit var phoneButton: Button

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
        productPrice.setText(product?.price_per_unit + " " + product?.price_type + "/" + product?.amount_type)
        productSeller.setText(product?.username)
        productName.setText(product?.title)
        if(product?.is_active.toString() == "true"){
            isActive.text = "Active"
        }
        else{
            isActive.text = "Inactive"
        }
        availableAmount.setText("Available amount: " + product?.units+product?.amount_type)

        orderButton.setOnClickListener(){
            findNavController().navigate(R.id.action_productDetailFragment_to_orderDialog)
        }

        emailButton.setOnClickListener(){
            Toast.makeText(context , "Messaging doesn't work!", Toast.LENGTH_SHORT).show()
        }

        phoneButton.setOnClickListener(){
            Toast.makeText(context , "The call cannot be made!", Toast.LENGTH_SHORT).show()
        }



    }

    private fun initializeView(view: View) {
        productName = view.findViewById(R.id.textView_product_name)
        productSeller = view.findViewById(R.id.textView_product_seller)
        productPrice = view.findViewById(R.id.textView_product_price)
        productDescription = view.findViewById(R.id.textView_product_description)
        productImage = view.findViewById(R.id.imageView_product)
        isActive = view.findViewById(R.id.textView_product_isActive)
        availableAmount = view.findViewById(R.id.textView_product_availableAmount)
        orderButton = view.findViewById(R.id.button_order)
        emailButton = view.findViewById(R.id.button_message)
        phoneButton = view.findViewById(R.id.button_phone)
    }

}

