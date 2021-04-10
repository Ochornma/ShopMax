package com.shopmax.app.ui.quote

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import android.text.InputType
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.shopmax.app.databinding.GetQuoteFragmentBinding
import com.shopmax.app.ui.base.BaseFragment
import com.shopmax.app.util.QuoteValidators
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception
import java.util.*


class GetQuoteFragment : BaseFragment<GetQuoteFragmentBinding, GetQuoteViewModel>() {
   // private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var dateFormatter: SimpleDateFormat
    override fun getViewModel(): Class<GetQuoteViewModel> {
        return GetQuoteViewModel::class.java
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun setUpViews() {
        super.setUpViews()
        dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.UK)


        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val datePickerDialog =
            context?.let {
                DatePickerDialog(
                    it,
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                        c.set(year, monthOfYear, dayOfMonth)
                        binding.shippingDateInput.setText(dateFormatter.format(c.time))
                    },
                    year,
                    month,
                    day
                )
            }

        binding.shippingDateInput.inputType = InputType.TYPE_NULL
        binding.shippingDateInput.requestFocus()
        binding.shippingDateInput.setOnClickListener {
            datePickerDialog?.show()
        }
       var pickupStatus: Boolean = false
        binding.pickUp.setOnCheckedChangeListener { _, isChecked ->
            pickupStatus = isChecked
        }
//r_name: String, r_address: String, p_name: String, p_address: String, ship_date: String, pickup: Boolean
        binding.next.setOnClickListener {
            try {
                val del_name = binding.delivCityNameInput.text.toString()
                val del_add = binding.delivAddInput.text.toString()
                val  pic_name = binding.pickCityNameInput.text.toString()
                val pic_add = binding.pickAddInput.text.toString()
                val date = binding.shippingDateInput.text.toString()
                //setUpDialog("Error", del_name)
                QuoteValidators.validateData(del_name, del_add, pic_name, pic_add, date, pickupStatus)
                val picker = viewModel.picker(pic_name, pic_add)
                val rec = viewModel.receiver(del_name, del_add)
                viewModel.nextPage(it, rec, picker, pickupStatus, date)
            }catch (e: Exception){
                e.message?.let { it1 -> setUpDialog("Error", it1) }
            }

        }
    }
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): GetQuoteFragmentBinding = GetQuoteFragmentBinding.inflate(inflater, container, false)



}