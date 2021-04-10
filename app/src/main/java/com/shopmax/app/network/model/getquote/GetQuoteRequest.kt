package com.shopmax.app.network.model.getquote

data class GetQuoteRequest(
    val packages: List<Package>,
    val pickupDetails: PickupDetails,
    val pickupRequested: Boolean,
    val plannedShipmentDateAndTime: String,
    val receiverDetails: ReceiverDetails
)