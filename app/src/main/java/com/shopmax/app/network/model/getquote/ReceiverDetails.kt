package com.shopmax.app.network.model.getquote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReceiverDetails(
    val address: String,
    val cityName: String
):Parcelable