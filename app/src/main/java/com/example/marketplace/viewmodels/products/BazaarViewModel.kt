package com.example.marketplace.viewmodels.products

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class BazaarViewModel (application : Application) : AndroidViewModel(application) {
    private var flag = 0

    fun setFlag(num : Int) {
        flag = num
    }

    fun getflag():Int{
        return flag
    }
}