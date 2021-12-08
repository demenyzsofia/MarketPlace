package com.example.marketplace.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.marketplace.R
import com.example.marketplace.repository.Repository
import com.example.marketplace.viewmodels.products.ListViewModel
import com.example.marketplace.viewmodels.products.ListViewModelFactory
import com.example.marketplace.viewmodels.user.UserDataViewModel
import com.example.marketplace.viewmodels.user.UserDataViewModelFactory

class ProfileFragment : Fragment() {
    private lateinit var userDataViewModel: UserDataViewModel
    private lateinit var personEmail: TextView
    private lateinit var personPhone: TextView
    private lateinit var userName: TextView


    val factoryList = ListViewModelFactory(Repository())
    private val listViewModel: ListViewModel by lazy{
        ViewModelProvider(requireActivity(),factoryList).get((ListViewModel::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = UserDataViewModelFactory(this.requireContext(), Repository(),listViewModel.getSellerName())
        userDataViewModel = ViewModelProvider(this, factory).get(UserDataViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        view?.apply {

            initializeView(this)
            userDataViewModel.userData.observe(viewLifecycleOwner){
                val oneuser = userDataViewModel.userData.value?.get(0)
                personEmail.text = oneuser?.email.toString()
                personPhone.text = oneuser?.phone_number.toString()
                userName.text = oneuser?.username.toString()
            }


        }
        return view
    }

    private fun initializeView(view: View) {
        personEmail = view.findViewById(R.id.textView_profile_email)
        personPhone = view.findViewById(R.id.textView_profile_phone)
        userName = view.findViewById(R.id.textView_profile_userName)
    }


}