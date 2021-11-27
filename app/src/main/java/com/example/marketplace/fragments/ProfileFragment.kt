package com.example.marketplace.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.marketplace.R
import com.example.marketplace.viewmodels.MarketViewModel

class ProfileFragment : Fragment() {

    private val marketViewModel: MarketViewModel by activityViewModels()
    private lateinit var personEmail: TextView
    private lateinit var personPhone: TextView
    private lateinit var userName: TextView

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

        }
        return view
    }

    private fun initializeView(view: View) {

    }


}