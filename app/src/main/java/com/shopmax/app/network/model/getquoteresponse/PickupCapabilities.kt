package com.shopmax.app.network.model.getquoteresponse
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PickupCapabilities(
    val pickup_additional_days: Int,
    val pickup_day_of_week: Int,
    val pickup_earliest: String,
    val pickup_latest: String
): Parcelable {
    val day: String = pickup_day_of_week.toString()
        get() = dayOfWeek(field)

    val additionalDay = pickup_additional_days.toString()
    get() = "$field days"

    fun dayOfWeek(i: String): String{
        val weekday = when(i) {
            "1" -> "Sunday"
            "2" -> "Monday"
            "3" -> "Tuesday"
            "4" -> "Wednesday"
            "5" -> "Thursday"
            "6" -> "Friday"
            "7" -> "Saturday"
            else -> "Unknown"
        }
        return  weekday
    }
}