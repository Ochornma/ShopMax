package com.shopmax.app.util

object QuoteValidators {

    fun validateData(r_name: String, r_address: String, p_name: String, p_address: String, ship_date: String, pickup: Boolean){
        if (r_name.trim().isEmpty()){
            throw ShopException("Enter Receiver's Name")
        }
        if (r_address.trim().isEmpty()){
            throw ShopException("Enter Receiver's Address")
        }
        if (pickup){
            if (p_name.trim().isEmpty()){
                throw ShopException("Enter Pickup Recipient's Name")
            }
            if (p_address.trim().isEmpty()){
                throw ShopException("Enter Pickup Recipient's Address")
            }
        }

        if (ship_date.trim().isEmpty()){
            throw ShopException("Select Shipping Data")
        }
    }
}