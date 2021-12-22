package com.example.marketplace.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.R
import com.example.marketplace.adapters.OrderDataAdapter
import com.example.marketplace.models.Order
import com.example.marketplace.repository.Repository
import com.example.marketplace.viewmodels.orders.OrderViewModel
import com.example.marketplace.viewmodels.orders.OrderViewModelFactory


class OrderFragment : Fragment() {
    private lateinit var recycler_view: RecyclerView
    private lateinit var adapter: OrderDataAdapter
    private lateinit var ongoingSales_button: Button
    private lateinit var ongoingOrders_button: Button

    val factory = OrderViewModelFactory(Repository())
    private val orderViewModel: OrderViewModel by lazy{
        ViewModelProvider(requireActivity(),factory).get((OrderViewModel::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_order, container, false)
        view?.apply {
            initializeView(this)
            registerListeners(this)
        }

        return view
    }

    private fun registerListeners(view: View) {
        ongoingSales_button.setOnClickListener(){
            findNavController().navigate(R.id.action_orderFragment_to_ongoingSalesFragment)
        }

        ongoingOrders_button.setOnClickListener(){
            findNavController().navigate(R.id.action_orderFragment_to_ongoingOrdersFragment)
        }
    }

    private fun initializeView(view: View) {
        ongoingSales_button = view.findViewById(R.id.button_ongoingsales)
        ongoingOrders_button = view.findViewById(R.id.button_ongoingorders)
        recycler_view = view.findViewById(R.id.recycler_view)
        setupRecyclerView()
        orderViewModel.orders.observe(viewLifecycleOwner){
            adapter.setData(orderViewModel.orders.value as ArrayList<Order>)
            adapter.notifyDataSetChanged()
        }

    }

    private fun setupRecyclerView(){
        adapter = OrderDataAdapter(ArrayList<Order>(), this.requireContext(), this)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this.context)
        recycler_view.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        recycler_view.setHasFixedSize(true)
    }


}