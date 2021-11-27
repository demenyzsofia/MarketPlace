package com.example.marketplace.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.marketplace.R
import com.example.marketplace.repository.Repository
import com.example.marketplace.viewmodels.*
import kotlinx.coroutines.launch

class ProfileSettingsFragment : Fragment() {
    private lateinit var profilSettingsViewModel: UserUpdateViewModel
    private lateinit var profile_image : ImageView
    private val pickImage = 100
    private var imageUri: Uri? = null
    private val marketViewModel: MarketViewModel by activityViewModels()
    private lateinit var fullPersonName : TextView
    private lateinit var userName : EditText
    private lateinit var phoneNumber : EditText
    private lateinit var emailAddress : EditText
    private lateinit var publishButton : Button

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
        fullPersonName = view.findViewById(R.id.textView_fullName)
        userName = view.findViewById(R.id.editText_settings_userName)
        phoneNumber = view.findViewById(R.id.editText_settings_phone)
        emailAddress = view.findViewById(R.id.editText_settings_email)
        publishButton = view.findViewById(R.id.button_settings)
    }

    private fun registerListeners(view: View) {
        profile_image.setOnClickListener{
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
        //beteszi a regisztracional beirt teljes nevet
        fullPersonName.setText(marketViewModel.getFullPersonName())


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
        }
        profilSettingsViewModel.token.observe(viewLifecycleOwner){


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