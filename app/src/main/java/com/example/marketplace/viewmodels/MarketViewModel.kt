package com.example.marketplace.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class MarketViewModel (application : Application) : AndroidViewModel(application) {
    private var fullPersonName: String =""

    fun getFullPersonName():String{
        return fullPersonName
    }

    fun getUpdateFullPersonName(name : String):String{
        fullPersonName=name
        return fullPersonName
    }
}

