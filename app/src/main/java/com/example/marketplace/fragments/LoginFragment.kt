package com.example.marketplace.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.marketplace.R
import com.example.marketplace.repository.Repository
import com.example.marketplace.viewmodels.LoginViewModel
import com.example.marketplace.viewmodels.LoginViewModelFactory
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit  var name_edittext : EditText
    private lateinit  var password_edittext : EditText
    private lateinit  var login_button : Button
    private lateinit var signup_button : Button
    private lateinit var clickhere_textview : TextView
    private lateinit var personImage : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = LoginViewModelFactory(this.requireContext(), Repository())
        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        view?.apply {
            initializeView(this)
            registerListeners(this)
        }

        return view

    }

    private fun initializeView(view: View) {
        name_edittext = view.findViewById(R.id.edittext_login_name)
        password_edittext = view.findViewById(R.id.edittext_login_password)
        login_button = view.findViewById(R.id.button_login)
        signup_button =  view.findViewById(R.id.button_signup)
        clickhere_textview = view.findViewById(R.id.textView_clickhere)

    }

    private fun registerListeners(view: View) {
        login_button.setOnClickListener {
            loginViewModel.user.value.let {
                if (it != null) {
                    it.username = name_edittext.text.toString()
                }
                if (it != null) {
                    it.password = password_edittext.text.toString()
                }
            }
            lifecycleScope.launch {
                loginViewModel.login()
            }

        }
        loginViewModel.token.observe(viewLifecycleOwner){
            Log.d("xxx", "navigate to list")
            findNavController().navigate(R.id.action_loginFragment_to_listFragment)
        }

        signup_button.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        clickhere_textview.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }
    }


}