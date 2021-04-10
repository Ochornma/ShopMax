package com.shopmax.app.ui.base

import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.shopmax.app.R
import dagger.hilt.android.AndroidEntryPoint


abstract class BaseFragment<B : ViewBinding, VM : ViewModel> : Fragment() {

    protected lateinit var viewModel: VM
    protected lateinit var binding: B
    open fun setUpViews() {

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getFragmentBinding(inflater, container)
        viewModel = ViewModelProvider(this).get(getViewModel())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    fun setUpDialog(title: String, message: String){
        val builder = context?.let { AlertDialog.Builder(it) }
        //set title for alert dialog
        builder?.setTitle(title)
        //set message for alert dialog
        builder?.setMessage(message)


        //performing positive action
        builder?.setPositiveButton("Okay"){dialogInterface, which ->
            dialogInterface.dismiss()
        }

        // Create the AlertDialog
        val alertDialog: AlertDialog = builder!!.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }



    abstract fun getViewModel(): Class<VM>

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B
}