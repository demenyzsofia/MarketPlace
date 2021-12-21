package com.example.marketplace.fragments


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.R
import com.example.marketplace.adapters.DataAdapter
import com.example.marketplace.models.Product
import com.example.marketplace.repository.Repository
import com.example.marketplace.viewmodels.products.*


class ListFragment : Fragment() , DataAdapter.OnItemClickListener, DataAdapter.OnItemLongClickListener{
    private lateinit var recycler_view: RecyclerView
    private lateinit var adapter: DataAdapter
    private lateinit var filteredList: ArrayList<Product>
    private lateinit var mSearchName: EditText

    val factory = ListViewModelFactory(Repository())
    private val listViewModel: ListViewModel by lazy{
        ViewModelProvider(requireActivity(),factory).get((ListViewModel::class.java))
    }

    private val viewModel: BazaarViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_list, container, false)
        view?.apply {
            mSearchName = findViewById(R.id.mSearchName)
            initializeView(this)
        }

        return view
    }

    private fun initializeView(view: View) {

        recycler_view = view.findViewById(R.id.recycler_view)
        setupRecyclerView()
        listViewModel.products.observe(viewLifecycleOwner){
            adapter.setData(listViewModel.products.value as ArrayList<Product>)
            adapter.notifyDataSetChanged()
        }
        if (viewModel.getflag()==1){
            mSearchName.visibility = View.VISIBLE
        }
        else{
            mSearchName.visibility = View.GONE
        }

        mSearchName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                filteredList = ArrayList()
                if (p0.toString() != "") {
                    for (item in listViewModel.products.value!!) {
                        if (item.title.toLowerCase().contains(p0.toString().toLowerCase())) {
                            filteredList.add(item)
                        }
                    }
                    setupRecyclerViewFiltered(filteredList)
                } else {
                    setupRecyclerView()
                }
            }

        })



    }
    private fun setupRecyclerViewFiltered(list: ArrayList<Product>) {

        //initlizing adapter
        adapter = DataAdapter( list,this.requireContext(), this, this)
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

    private fun setupRecyclerView(){
        adapter = DataAdapter(ArrayList<Product>(), this.requireContext(), this, this)
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
        viewModel.setFlag(0)
        listViewModel.updateCurrentPosition(position)
        findNavController().navigate(R.id.action_listFragment_to_productDetailFragment)
    }

    override fun onItemLongClick(position: Int) {

    }

    override  fun onSellerNameClick(position : Int){
        viewModel.setFlag(0)
        listViewModel.updateCurrentPosition(position)
        findNavController().navigate(R.id.action_listFragment_to_profileFragment)
    }

    override  fun onOrderButtonClick(position: Int){
        viewModel.setFlag(0)
        listViewModel.updateCurrentPosition(position)
        findNavController().navigate(R.id.action_listFragment_to_orderDialog)
    }





}



