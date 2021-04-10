package com.shopmax.app.network.model.getquote

data class Dimensions(
    val height: Int,
    val length: Int,
    val width: Int
){
    val h = height.toString()
    get() = "$field m"

    val w = width.toString()
        get() = "$field m"

    val l = length.toString()
        get() = "$field m"
}