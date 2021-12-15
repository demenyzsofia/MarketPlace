package com.example.marketplace.fragments

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
import com.example.marketplace.adapters.MyMarketDataAdapter
import com.example.marketplace.models.Product
import com.example.marketplace.repository.Repository
import com.example.marketplace.viewmodels.products.ListViewModel
import com.example.marketplace.viewmodels.products.ListViewModelFactory
import com.example.marketplace.viewmodels.user.LoginViewModel
import com.example.marketplace.viewmodels.user.LoginViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MyMarketFragment : Fragment() , MyMarketDataAdapter.OnItemClickListener {
    private lateinit var recycler_view: RecyclerView
    private lateinit var adapter: MyMarketDataAdapter
    private lateinit var addButton: FloatingActionButton

    val factory = ListViewModelFactory(Repository())
    private val listViewModel: ListViewModel by lazy{
        ViewModelProvider(requireActivity(),factory).get((ListViewModel::class.java))
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
        val view =  inflater.inflate(R.layout.fragment_my_market, container, false)
        view?.apply {

            initializeView(this)
            registerListeners(this)
        }

        return view
    }

    private fun registerListeners(view: View) {
        addButton.setOnClickListener{
            findNavController().navigate(R.id.action_myMarketFragment_to_addProductFragment)
        }
    }

    private fun initializeView(view: View) {
        addButton = view.findViewById(R.id.add_product_button)
        recycler_view = view.findViewById(R.id.recycler_view)
        setupRecyclerView()
        listViewModel.products.observe(viewLifecycleOwner){
            var products = listViewModel.products.value as ArrayList<Product>
            val prod : ArrayList<Product> = products.filter { product -> product.username == loginViewModel.user.value?.username.toString() } as ArrayList<Product>
            listViewModel.updateMyProducts(prod)
            adapter.setData( prod )
            adapter.notifyDataSetChanged()
        }



    }

    private fun setupRecyclerView(){
        adapter = MyMarketDataAdapter(ArrayList<Product>(), this.requireContext(), this)
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

    override fun onItemClick(position: Int) {
        listViewModel.updateCurrentPosition(position)
        findNavController().navigate(R.id.action_myMarketFragment_to_myProductDetailFragment)
    }

}
