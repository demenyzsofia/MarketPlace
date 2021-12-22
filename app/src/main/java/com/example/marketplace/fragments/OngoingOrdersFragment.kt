package com.example.marketplace.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.R
import com.example.marketplace.adapters.OngoingOrdersDataAdapter
import com.example.marketplace.models.Order
import com.example.marketplace.repository.Repository
import com.example.marketplace.viewmodels.orders.OrderViewModel
import com.example.marketplace.viewmodels.orders.OrderViewModelFactory
import com.example.marketplace.viewmodels.orders.RemoveOrderViewModel
import com.example.marketplace.viewmodels.orders.RemoveOrderViewModelFactory
import com.example.marketplace.viewmodels.user.LoginViewModel
import com.example.marketplace.viewmodels.user.LoginViewModelFactory


class OngoingOrdersFragment : Fragment(), OngoingOrdersDataAdapter.OnItemClickListener {
    private lateinit var removeOrderViewModel: RemoveOrderViewModel
    private lateinit var recycler_view: RecyclerView
    private lateinit var adapter: OngoingOrdersDataAdapter


    val factory = OrderViewModelFactory(Repository())
    private val orderViewModel: OrderViewModel by lazy{
        ViewModelProvider(requireActivity(),factory).get((OrderViewModel::class.java))
    }

    val factory_login = LoginViewModelFactory( Repository())
    private val loginViewModel: LoginViewModel by lazy{
        ViewModelProvider(requireActivity(),factory_login).get((LoginViewModel::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_ongoing_orders, container, false)
        view?.apply {
            initializeView(this)
        }

        return view
    }

    private fun initializeView(view: View) {
        recycler_view = view.findViewById(R.id.recycler_view)
        setupRecyclerView()
        orderViewModel.orders.observe(viewLifecycleOwner){
            var orders = orderViewModel.orders.value as ArrayList<Order>
            val ord : ArrayList<Order> = orders.filter { order -> order.username == loginViewModel.user.value?.username.toString() } as ArrayList<Order>

            orderViewModel.updateMyOrders(ord)
            adapter.setData(ord)
            adapter.notifyDataSetChanged()
        }


    }

    private fun setupRecyclerView(){
        adapter = OngoingOrdersDataAdapter(ArrayList<Order>(), this.requireContext(),this)
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

    override fun onDeleteClick(position: Int) {
        val dialogBuilder = AlertDialog.Builder(activity)
        dialogBuilder.setMessage("Do you want to delete this Order?")
            .setPositiveButton("Yes!  ", DialogInterface.OnClickListener { dialog, id ->
                orderViewModel.updateCurrentPosition(position)
                val factory = RemoveOrderViewModelFactory(this.requireContext(), Repository(),orderViewModel.getOrderId())
                removeOrderViewModel = ViewModelProvider(this, factory).get(
                    RemoveOrderViewModel::class.java)
                orderViewModel.getOrders()
                findNavController().navigate(R.id.action_ongoingOrdersFragment_self)
            })
            .setNegativeButton("No!", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Secure deletion")
        alert.show()


    }


}