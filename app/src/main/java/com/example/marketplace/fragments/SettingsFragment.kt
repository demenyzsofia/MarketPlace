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
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.marketplace.R
import com.example.marketplace.viewmodels.MarketViewModel

class SettingsFragment : Fragment() {
    private lateinit var profile_image : ImageView
    private val pickImage = 100
    private var imageUri: Uri? = null
    private val marketViewModel: MarketViewModel by activityViewModels()
    private lateinit var fullPersonName : TextView
    private lateinit var userName : EditText
    private lateinit var phoneNumber : EditText
    private lateinit var emailAddress : EditText
    private lateinit var password : EditText
    private lateinit var publishButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        view?.apply {

            initializeView(this)
            registerListeners(this)
        }

        return view
    }

    private fun initializeView(view: View) {
        profile_image = view.findViewById(R.id.imageView_profile)
        fullPersonName = view.findViewById(R.id.textView_fullName)
        userName = view.findViewById(R.id.editText_profile_userName)
        phoneNumber = view.findViewById(R.id.editText_profile_phone)
        emailAddress = view.findViewById(R.id.editText_profile_email)
        password = view.findViewById(R.id.editText_profile_password)
        publishButton = view.findViewById(R.id.button_profile)
    }

    private fun registerListeners(view: View) {
        profile_image.setOnClickListener{
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
        //beteszi a regisztracional beirt teljes nevet
        fullPersonName.setText(marketViewModel.getFullPersonName())

        publishButton.setOnClickListener{
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