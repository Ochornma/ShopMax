package com.shopmax.app.util

import java.lang.Exception

object QuoteValidators {

    fun validateData(r_name: String, r_address: String, p_name: String, p_address: String, ship_date: String, pickup: Boolean){
        if (r_name.trim().isEmpty()){
            throw ShopException("Enter Delivery City Name")
        }
        if (r_address.trim().isEmpty()){
            throw ShopException("Enter Delivery Address")
        }
        if (pickup){
            if (p_name.trim().isEmpty()){
                throw ShopException("Enter Pickup City Name")
            }
            if (p_address.trim().isEmpty()){
                throw ShopException("Enter Pickup City Address")
            }
        }

        if (ship_date.trim().isEmpty()){
            throw ShopException("Select Shipping Data")
        }
    }

    fun validateData2(p_name: String, p_weight: String, p_lenght: String, p_width: String, p_height: String){
        if (p_name.trim().isEmpty()){
            throw ShopException("Enter Package Name")
        }
        if (p_weight.trim().isEmpty()){
            throw ShopException("Enter Package Weight")
        }

        try {
            p_weight.trim().toInt()
        }catch (e: Exception){
            throw ShopException("Enter a Digit for Weight")
        }

            if (p_lenght.trim().isEmpty()){
                throw ShopException("Enter Package Length")
            }

        try {
            p_lenght.trim().toInt()
        }catch (e: Exception){
            throw ShopException("Enter a Digit for Length")
        }
            if (p_height.trim().isEmpty()){
                throw ShopException("Enter Package Height")
            }

        try {
            p_height.trim().toInt()
        }catch (e: Exception){
            throw ShopException("Enter a Digit for Height")
        }

        if (p_width.trim().isEmpty()){
            throw ShopException("Enter Package Width")
        }

        try {
            p_width.trim().toInt()
        }catch (e: Exception){
            throw ShopException("Enter a Digit for Width")
        }
    }
}