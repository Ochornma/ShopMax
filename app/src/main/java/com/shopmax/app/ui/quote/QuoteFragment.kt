package com.shopmax.app.ui.quote

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.shopmax.app.R
import com.shopmax.app.databinding.QuoteFragmentBinding
import com.shopmax.app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


class QuoteFragment : BaseFragment<QuoteFragmentBinding, QuoteViewModel>() {

    override fun getViewModel(): Class<QuoteViewModel> {
        return QuoteViewModel::class.java
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): QuoteFragmentBinding {
        return QuoteFragmentBinding.inflate(inflater, container, false)
    }

    override fun setUpViews() {
        super.setUpViews()
        val args: QuoteFragmentArgs by navArgs()
        val response = args.quote
        binding.response = response
    }

}