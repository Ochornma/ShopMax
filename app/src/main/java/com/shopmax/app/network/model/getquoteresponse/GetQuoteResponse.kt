package com.shopmax.app.network.model.getquoteresponse

data class GetQuoteResponse(
    val `data`: Data,
    val message: String,
    val status: Boolean
)