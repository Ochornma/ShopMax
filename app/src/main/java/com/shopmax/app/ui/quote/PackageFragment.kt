package com.shopmax.app.ui.quote

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Slide
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.shopmax.app.R
import com.shopmax.app.databinding.PackageFragmentBinding
import com.shopmax.app.network.model.getquote.Package
import com.shopmax.app.util.QuoteValidators
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class PackageFragment : Fragment(), EditQuote {

    private lateinit var adapter: QuoteAdapter
    private var packs:MutableList<Package> = ArrayList()
    private var edit = false
    private var position = 0
    private lateinit var binding: PackageFragmentBinding
    private  val viewModel: PackageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.package_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getQuote()
    }


    fun setUpViews() {

        val args: PackageFragmentArgs by navArgs()
        val receiver = args.reciever
        val picker = args.pickup
        val pick = args.pickuprequest
        val date = args.shippingdate

        adapter = QuoteAdapter(this)

        binding.addPackage.setOnClickListener {
            edit = false
            toggle(true)
        }
        binding.closeCard.setOnClickListener {

            toggle(false)
            clear()
        }

        binding.submit.setOnClickListener {
            val len = binding.packLenghtInput.text.toString()
            val wid = binding.packWidthInput.text.toString()
            val wei = binding.packWeightInput.text.toString()
            val name = binding.packNameInput.text.toString()
            val hei = binding.packHeightInput.text.toString()
            try {
                QuoteValidators.validateData2(name, wei, len, wid, hei)
                val dime = viewModel.creatDimen(len, wid, hei)
                val pack = viewModel.createPackage(dime, name, wei)
                if (!edit){
                    packs.add(pack)
                }else{
                 packs.set(position, pack)
                }
                adapter.setPACK(packs)
                toggle(false)
                clear()
            }catch (e: Exception){
                e.message?.let { it1 -> setUpDialog("Error", it1) }
            }

            binding.recyclerView.setHasFixedSize(true)
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.adapter = adapter

        }

        binding.requestQuote.setOnClickListener {
            val data = viewModel.createRequest(picker, pick, date, receiver, packs)
            viewModel.quote(data)
            binding.packageHolder.visibility = View.INVISIBLE
            binding.addPackageCard.visibility = View.GONE
            binding.prgress.visibility = View.VISIBLE
        }



    }
    private fun toggle(show: Boolean) {
        val redLayout: View = binding.addPackageCard
        val parent: ViewGroup = binding.parent
        val transition: Transition = Slide(Gravity.BOTTOM)
        transition.duration = 600
        transition.addTarget(binding.addPackageCard)
        TransitionManager.beginDelayedTransition(parent, transition)
        redLayout.visibility = if (show) View.VISIBLE else View.GONE
            binding.addPackage.isClickable = !show
            binding.requestQuote.isClickable = !show

    }

    override fun edit(position: Int) {
    toggle(true)
        edit = true
        this.position = position
        binding.packNameInput.setText(packs[position].name)
        binding.packWeightInput.setText(packs[position].weight.toString())
        binding.packHeightInput.setText(packs[position].dimensions.height.toString())
        binding.packLenghtInput.setText(packs[position].dimensions.length.toString())
        binding.packWidthInput.setText(packs[position].dimensions.width.toString())
    }

    override fun remove(position: Int) {
       packs.removeAt(position)
        binding.recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    fun clear(){
        binding.packNameInput.setText("")
        binding.packWeightInput.setText("")
        binding.packHeightInput.setText("")
        binding.packLenghtInput.setText("")
        binding.packWidthInput.setText("")
    }

    fun getQuote(){
        lifecycleScope.launchWhenStarted {
            viewModel.response.collect {
                when(it){
                    is PackageViewModel.Event.Success ->{
                        val action = PackageFragmentDirections.actionPackageFragmentToQuoteFragment(it.response)
                        findNavController().navigate(action)
                    }

                    else ->{
                        binding.packageHolder.visibility = View.VISIBLE
                        binding.addPackageCard.visibility = View.GONE
                        binding.prgress.visibility = View.GONE
                        setUpDialog("Error", "Error in Requesting for Quote")
                    }
                }

            }
        }
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
}