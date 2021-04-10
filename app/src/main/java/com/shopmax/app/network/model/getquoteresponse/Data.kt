package com.shopmax.app.network.model.getquoteresponse

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    val delivery_capabilities: DeliveryCapabilities,
    val local_product_country_code: String,
    val pickup_capabilities: PickupCapabilities,
    val product_code: String,
    val product_name: String,
    val total_price: Double
): Parcelable {
    var price: String = total_price.toString()
        // Custom Getter
        get() {
            return field
        }
}