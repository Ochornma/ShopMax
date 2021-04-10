package com.shopmax.app.ui.quote

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shopmax.app.R

class PackageFragment : Fragment() {

    companion object {
        fun newInstance() = PackageFragment()
    }

    private lateinit var viewModel: PackageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.package_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PackageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}