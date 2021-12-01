package com.example.marketplace.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy.LOG
import com.example.marketplace.R
import com.example.marketplace.models.OneUser
import com.example.marketplace.models.Product
import com.example.marketplace.repository.Repository
import com.example.marketplace.viewmodels.*

class ProfileFragment : Fragment() {

    private lateinit var personEmail: TextView
    private lateinit var personPhone: TextView
    private lateinit var userName: TextView


    val factory = UserDataViewModelFactory(Repository())
    private val userDataViewModel: UserDataViewModel by lazy{
        ViewModelProvider(requireActivity(),factory).get((UserDataViewModel::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        view?.apply {

            initializeView(this)
            val oneuser = userDataViewModel.userData.value?.get(0)
            personEmail.text = oneuser?.email.toString()
            personPhone.text = oneuser?.phone_number.toString()
            userName.text = oneuser?.username.toString()

        }
        return view
    }

    private fun initializeView(view: View) {
        personEmail = view.findViewById(R.id.textView_profile_email)
        personPhone = view.findViewById(R.id.textView_profile_phone)
        userName = view.findViewById(R.id.textView_profile_userName)
    }


}