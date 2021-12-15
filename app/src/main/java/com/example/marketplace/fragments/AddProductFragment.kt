package com.example.marketplace.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.marketplace.R
import com.example.marketplace.repository.Repository
import com.example.marketplace.viewmodels.products.AddProductViewModel
import com.example.marketplace.viewmodels.products.AddProductViewModelFactory
import com.example.marketplace.viewmodels.user.LoginViewModel
import com.example.marketplace.viewmodels.user.LoginViewModelFactory
import com.example.marketplace.viewmodels.user.UserDataViewModel
import kotlinx.coroutines.launch


class AddProductFragment : Fragment() {
    private lateinit var addProductViewModel: AddProductViewModel
    private lateinit var price_amount_spinner : Spinner
    private lateinit var available_amount_spinner : Spinner
    private lateinit var price : EditText
    private lateinit var addButton : Button
    private lateinit var title : EditText
    private lateinit var description : EditText
    private lateinit var is_active  : Switch
    private lateinit var amount_type  : Spinner
    private lateinit var price_type  : Spinner
    private lateinit var username  : EditText
    private lateinit var email  : EditText
    private lateinit var phone  : EditText

    val factory_login = LoginViewModelFactory( Repository())
    private val loginViewModel: LoginViewModel by lazy{
        ViewModelProvider(requireActivity(),factory_login).get((LoginViewModel::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = AddProductViewModelFactory(this.requireContext(), Repository())
        addProductViewModel = ViewModelProvider(this, factory).get(AddProductViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add_product, container, false)
        view?.apply {
            initializeView(this)
            //price/amount
            ArrayAdapter.createFromResource(
                context,
                R.array.price_amount,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                price_amount_spinner.adapter = adapter
            }
            //available amount
            ArrayAdapter.createFromResource(
                context,
                R.array.available_amount,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                available_amount_spinner.adapter = adapter
            }

            registerListeners(this)
        }

        return view
    }

    private fun registerListeners(view: View) {
        username.setText(loginViewModel.user.value!!.username)
        email.setText(loginViewModel.user.value!!.email)
        phone.setText(loginViewModel.user.value!!.phone_number)

        addButton.setOnClickListener{
            addProductViewModel.product.value.let {
                if (it != null) {
                    it.title = title.text.toString()
                }
                if (it != null) {
                    it.description = description.text.toString()
                }
                if (it != null) {
                    it.amount_type = amount_type.toString()
                }
                if (it != null) {
                    it.price_type = price_type.toString()
                }
                if (it != null) {
                    it.price_per_unit = price.toString()
                }
                if (it != null) {
                    if (is_active.isChecked){
                        it.is_active = true
                    }
                    else{
                        it.is_active = false
                    }

                }
            }
            lifecycleScope.launch {
                addProductViewModel.addProduct()
            }
            findNavController().navigate(R.id.action_addProductFragment_to_myMarketFragment)
        }
    }


    private fun initializeView(view: View) {
        price_amount_spinner = view.findViewById(R.id.spinner_addProduct_price_amount)
        available_amount_spinner = view.findViewById(R.id.spinner_addProduct_available_amount)
        addButton = view.findViewById(R.id.button_launch_myfair)
        title = view.findViewById(R.id.editText_addProduct_title)
        description = view.findViewById(R.id.editText_addProduct_description)
        is_active = view.findViewById(R.id.switch_addProduct)
        price = view.findViewById(R.id.editText_addProduct_price)
        amount_type = view.findViewById(R.id.spinner_addProduct_available_amount)
        price_type = view.findViewById(R.id.spinner_addProduct_price_amount)
        username = view.findViewById(R.id.editText_name_addproduct)
        email = view.findViewById(R.id.editText_email_addproduct)
        phone = view.findViewById(R.id.editText_phone_addproduct)
    }

}