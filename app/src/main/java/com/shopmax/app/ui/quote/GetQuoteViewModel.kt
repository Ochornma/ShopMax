package com.shopmax.app.ui.quote

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.shopmax.app.R
import com.shopmax.app.network.model.getquote.PickupDetails
import com.shopmax.app.network.model.getquote.ReceiverDetails

class GetQuoteViewModel : ViewModel() {


    fun nextPage(view: View, rec: ReceiverDetails, pickupDetails: PickupDetails,pick: Boolean, date: String){
        val action = GetQuoteFragmentDirections.actionGetQuoteFragmentToPackageFragment(rec, pickupDetails, pick, date)
        view.findNavController().navigate(action)
    }

    fun receiver(name: String, addre: String): ReceiverDetails{
        return  ReceiverDetails(addre, name)
    }

    fun picker(name: String, addre: String): PickupDetails{
        return  PickupDetails(addre, name)
    }
}