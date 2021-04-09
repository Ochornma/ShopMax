package com.shopmax.app.ui.order

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shopmax.app.R

class PlaceOrderFragment : Fragment() {

    companion object {
        fun newInstance() = PlaceOrderFragment()
    }

    private lateinit var viewModel: PlaceOrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.place_order_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlaceOrderViewModel::class.java)
        // TODO: Use the ViewModel
    }

}