package com.example.marketplace.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.marketplace.R
import com.example.marketplace.repository.Repository
import com.example.marketplace.viewmodels.user.LoginViewModel
import com.example.marketplace.viewmodels.user.LoginViewModelFactory
import com.example.marketplace.viewmodels.user.UserUpdateViewModel
import com.example.marketplace.viewmodels.user.UserUpdateViewModelFactory
import kotlinx.coroutines.launch

class ProfileSettingsFragment : Fragment() {
    private lateinit var profilSettingsViewModel: UserUpdateViewModel
    private lateinit var profile_image : ImageView
    private val pickImage = 100
    private var imageUri: Uri? = null
    private lateinit var userName : EditText
    private lateinit var phoneNumber : EditText
    private lateinit var emailAddress : EditText
    private lateinit var publishButton : Button

    val factory = LoginViewModelFactory( Repository())
    private val loginViewModel: LoginViewModel by lazy{
        ViewModelProvider(requireActivity(),factory).get((LoginViewModel::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = UserUpdateViewModelFactory(this.requireContext(), Repository())
        profilSettingsViewModel = ViewModelProvider(this, factory).get(UserUpdateViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile_settings, container, false)
        view?.apply {

            initializeView(this)
            registerListeners(this)
        }

        return view
    }

    private fun initializeView(view: View) {
        profile_image = view.findViewById(R.id.imageView_profile)
        userName = view.findViewById(R.id.editText_settings_userName)
        phoneNumber = view.findViewById(R.id.editText_settings_phone)
        emailAddress = view.findViewById(R.id.editText_settings_email)
        publishButton = view.findViewById(R.id.button_settings)
        userName.setText(loginViewModel.user.value?.username.toString())
        emailAddress.setText(loginViewModel.user.value?.email.toString())
        phoneNumber.setText(loginViewModel.user.value?.phone_number.toString())
    }

    private fun registerListeners(view: View) {
        profile_image.setOnClickListener{
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }

        publishButton.setOnClickListener {
            profilSettingsViewModel.user.value.let {
                if (it != null) {
                    it.username = userName.text.toString()
                }
                if (it != null) {
                    it.email = emailAddress.text.toString()
                }
                if (it != null) {
                    it.phone_number = phoneNumber.text.toString()
                }
            }
            lifecycleScope.launch {
                profilSettingsViewModel.userUpdate()
            }
            emailAddress.text = emailAddress.text
            emailAddress.setEnabled(false)
            userName.text =  userName.text
            userName.setEnabled(false)
            phoneNumber.text = phoneNumber.text
            phoneNumber.setEnabled(false)
            publishButton.setText("Go To Shopping!")
            Toast.makeText(context , "The update was successful!", Toast.LENGTH_SHORT).show()
            publishButton.setOnClickListener(){
                findNavController().navigate(R.id.action_profileSettingsFragment_to_listFragment)
            }
        }






    }
    //kep beszurasahoz a galeryabol
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            profile_image.setImageURI(imageUri)
        }
    }




}