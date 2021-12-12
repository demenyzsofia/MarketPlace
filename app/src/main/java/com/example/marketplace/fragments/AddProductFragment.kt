package com.example.marketplace.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.marketplace.R

class AddProductFragment : Fragment() {
    private lateinit var price_amount_spinner : Spinner
    private lateinit var available_amount_spinner : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        }

        return view
    }


    private fun initializeView(view: View) {
        price_amount_spinner = view.findViewById(R.id.spinner_addProduct_price_amount)
        available_amount_spinner = view.findViewById(R.id.spinner_addProduct_available_amount)
    }

}