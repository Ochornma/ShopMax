package com.shopmax.app.network.model.getquoteresponse

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetQuoteResponse(
    val `data`: Data,
    val message: String,
    val status: Boolean
): Parcelable