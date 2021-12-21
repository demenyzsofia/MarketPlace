package com.example.marketplace.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.marketplace.R

class CompletedOrderDialog : DialogFragment() {
    private lateinit var closeButton : Button
    private lateinit var textForOngoinOrders : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.completed_order_dialog, container, false)
        view?.apply {
            initializeView(this)
            registerListeners(this)
        }

        return view
    }

    private fun registerListeners(view: View) {
        closeButton.setOnClickListener{
            findNavController().navigate(R.id.action_completedOrderDialog_to_listFragment)
        }
        textForOngoinOrders.setOnClickListener(){
            findNavController().navigate(R.id.action_completedOrderDialog_to_ongoingOrdersFragment)
        }

    }

    private fun initializeView(view: View) {
        closeButton = view.findViewById(R.id.button_completed_order_close)
        textForOngoinOrders = view.findViewById(R.id.textView_comleted_order)
    }

}