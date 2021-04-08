package com.shopmax.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.shopmax.app.databinding.HomeFragmentBinding
import com.shopmax.ui.base.BaseFragment

class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>() {



    override fun getViewModel(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): HomeFragmentBinding = HomeFragmentBinding.inflate(inflater, container, false)

    override fun setUpViews() {
        super.setUpViews()
    }

}