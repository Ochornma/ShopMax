package com.shopmax.app.network.model.getquote

import com.shopmax.app.network.model.getquote.Dimensions

data class Package(
    val dimensions: Dimensions,
    val name: String,
    val weight: Int
)