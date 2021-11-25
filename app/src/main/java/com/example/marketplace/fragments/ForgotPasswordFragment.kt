package com.example.marketplace.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.marketplace.R
import com.example.marketplace.repository.Repository
import com.example.marketplace.viewmodels.ForgotPasswordViewModel
import com.example.marketplace.viewmodels.ForgotPasswordViewModelFactory
import kotlinx.coroutines.launch


class ForgotPasswordFragment : Fragment() {
    private lateinit var forgotPasswordViewModel: ForgotPasswordViewModel
    private lateinit var emailMe_button : Button
    private lateinit var name_edittext : EditText
    private lateinit var email_edittext : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ForgotPasswordViewModelFactory(this.requireContext(), Repository())
        forgotPasswordViewModel = ViewModelProvider(this, factory).get(ForgotPasswordViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forgot_password, container, false)
        view?.apply {
            initializeView(this)
            registerListeners(this)
        }

        return view
    }

    private fun registerListeners(view: View) {
        emailMe_button.setOnClickListener{
            forgotPasswordViewModel.user.value.let {
                if (it != null) {
                    it.username = name_edittext.text.toString()
                }
                if (it != null) {
                    it.email = email_edittext.text.toString()
                }
            }
            lifecycleScope.launch {
                forgotPasswordViewModel.resetPassword()
            }
        }
        forgotPasswordViewModel.token.observe(viewLifecycleOwner){
            Toast.makeText(context , "Email in progress...", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_forgotPasswordFragment_to_loginFragment)
        }

    }

    private fun initializeView(view: View) {
        emailMe_button= view.findViewById(R.id.button_forgot_password)
        name_edittext = view.findViewById(R.id.editText_forgotPassword_userName)
        email_edittext = view.findViewById(R.id.editText_forgotPassword_email)

    }


}