package com.example.marketplace.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.marketplace.R
import com.example.marketplace.repository.Repository
import com.example.marketplace.viewmodels.MarketViewModel
import com.example.marketplace.viewmodels.RegisterViewModel
import com.example.marketplace.viewmodels.RegisterViewModelFactory
import kotlinx.coroutines.launch


class RegisterFragment : Fragment() {
    private lateinit var registerViewModel: RegisterViewModel
    private val marketViewModel: MarketViewModel by activityViewModels()

    private lateinit var register_login : TextView
    private lateinit var register_button : Button
    private lateinit  var username_edittext : EditText
    private lateinit  var password_edittext : EditText
    private lateinit  var email_edittext : EditText
    private  lateinit var phonenumber_edittext : EditText
    private lateinit var full_name : EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = RegisterViewModelFactory(this.requireContext(), Repository())
        registerViewModel = ViewModelProvider(this, factory).get(RegisterViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        view?.apply {
            initializeView(this)
            registerListeners(this)
        }

        return view
    }

    private fun registerListeners(view: View) {
        register_login.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        register_button.setOnClickListener {
            marketViewModel.getUpdateFullPersonName(full_name.text.toString())

            registerViewModel.user.value.let {
                if (it != null) {
                    it.username = username_edittext.text.toString()
                }
                if (it != null) {
                    it.password = password_edittext.text.toString()
                }
                if (it != null) {
                    it.email = email_edittext.text.toString()
                }
                if (it != null) {
                    it.phone_number = phonenumber_edittext.text.toString()
                }
            }
            lifecycleScope.launch {
                registerViewModel.register()
            }

        }
        registerViewModel.token.observe(viewLifecycleOwner){
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

        }


    }

    private fun initializeView(view: View) {
        register_login = view.findViewById(R.id.textView_register_login)
        register_button = view.findViewById(R.id.button_register)
        username_edittext = view.findViewById(R.id.editText_register_username)
        password_edittext = view.findViewById(R.id.editText_register_password)
        email_edittext = view.findViewById(R.id.editText_register_email)
        phonenumber_edittext = view.findViewById(R.id.editText_register_phonenumber)
        full_name = view.findViewById(R.id.editText_register_fullname)
    }

}