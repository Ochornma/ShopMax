package com.shopmax.app.ui.home

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.shopmax.app.R

class HomeViewModel : ViewModel() {

    fun getQuote(view: View){
        view.findNavController().navigate(R.id.action_homeFragment_to_getQuoteFragment)
    }

    fun placeOrder(view: View){
        view.findNavController().navigate(R.id.action_homeFragment_to_placeOrderFragment)
    }

    fun trackOrder(view: View){
        view.findNavController().navigate(R.id.action_homeFragment_to_trackOrderFragment)
    }

}