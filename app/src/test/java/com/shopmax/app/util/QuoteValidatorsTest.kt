package com.shopmax.app.util

import org.junit.Assert.*
import org.junit.Test


class QuoteValidatorsTest {

 @Test
 fun `test if receiver name is empty`(){
     val exception: Throwable = assertThrows(ShopException::class.java){
          QuoteValidators.validateData(" ", "jj", "kk", "ll", "kk", true)
     }
     assertEquals("Enter Delivery City Name", exception.message)
 }

    @Test
    fun `test if receiver address is empty`(){
        val exception: Throwable = assertThrows(ShopException::class.java){
            QuoteValidators.validateData("hh", " ", "kk", "ll", "kk", true)
        }
        assertEquals("Enter Delivery Address", exception.message)
    }
    @Test
    fun `test if ship date is empty`(){
        val exception: Throwable = assertThrows(ShopException::class.java){
            QuoteValidators.validateData("ff", "jj", "kk", "ll", " ", true)
        }
        assertEquals("Select Shipping Data", exception.message)
    }
    @Test
    fun `test if picker name is empty`(){
        val exception: Throwable = assertThrows(ShopException::class.java){
           QuoteValidators.validateData("ff", "jj", " ", "ll", "kk", true)
        }
        assertEquals("Enter Pickup City Name", exception.message)
    }
    @Test
    fun `test if picker address is empty`(){
        val exception: Throwable = assertThrows(ShopException::class.java){
            QuoteValidators.validateData("ff", "jj", "kk", " ", "kk", true)
        }
        assertEquals("Enter Pickup City Address", exception.message)
    }





}