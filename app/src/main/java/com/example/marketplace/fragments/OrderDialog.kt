package com.example.marketplace.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.marketplace.R
import com.example.marketplace.models.Product
import com.example.marketplace.repository.Repository
import com.example.marketplace.viewmodels.ListViewModel
import com.example.marketplace.viewmodels.ListViewModelFactory

class OrderDialog : DialogFragment() {
    private lateinit var orderButton: Button
    private lateinit var cancelButton: Button
    private lateinit var sellerName: TextView
    private lateinit var productName: TextView
    private lateinit var productPrice: TextView
    private lateinit var isActive: TextView
    private lateinit var amount: EditText


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
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.dialog_order, container, false)
        view?.apply {
            initializeView(this)
            registerListeners(this)
        }

        return view
    }

    private fun registerListeners(view: View) {
        val product: Product? = listViewModel.getOneProduct()

        productPrice.text = product?.price_per_unit + " RON"
        sellerName.text = product?.username
        productName.text = product?.title
        if (product?.is_active == true){
            isActive.text = "Active"
        }
        else{
            isActive.text = "Not active"
        }
        amount.setHint(product?.amount_type.toString())
        amount.setText( product?.units.toString())


        cancelButton.setOnClickListener{
            findNavController().navigate(R.id.action_orderDialog_to_listFragment)
        }

        orderButton.setOnClickListener{
            findNavController().navigate(R.id.action_orderDialog_to_completedOrderDialog)


        }
    }

    private fun initializeView(view: View) {
        orderButton = view.findViewById(R.id.button_order_sendorder)
        cancelButton = view.findViewById(R.id.button_completed_order_close)
        sellerName = view.findViewById(R.id.textView_order_sellerName)
        productName = view.findViewById(R.id.textView_order_productName)
        productPrice = view.findViewById(R.id.textView_order_price)
        isActive = view.findViewById(R.id.textView_order_isactive)
        amount = view.findViewById(R.id.editText_order_amount)

    }


}