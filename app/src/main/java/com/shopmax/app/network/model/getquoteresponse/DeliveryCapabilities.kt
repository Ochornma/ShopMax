package com.shopmax.app.network.model.getquoteresponse

import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import java.text.ParseException
import java.util.*

import kotlinx.parcelize.Parcelize

@Parcelize
data class DeliveryCapabilities(
    val delivery_additional_days: Int,
    val delivery_day_of_week: Int,
    val estimated_delivery_date_and_time: String,
    val total_transit_days: Int
): Parcelable {

    val day: String = delivery_day_of_week.toString()
        get() = dayOfWeek(field)

    val additionalDay = delivery_additional_days.toString()
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

    /** Converting from String to Date **/


    @RequiresApi(Build.VERSION_CODES.N)
    fun String.getDateWithServerTimeStamp(): Date? {
        val dateFormat = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            Locale.getDefault()
        )
        dateFormat.timeZone = TimeZone.getTimeZone("GMT")  // IMP !!!
        try {
            return dateFormat.parse(this)
        } catch (e: ParseException) {
            return null
        }
    }
        /** Converting from Date to String**/
        @RequiresApi(Build.VERSION_CODES.N)
        fun Date.getStringTimeStampWithDate(): String {
            val dateFormat = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                Locale.getDefault()
            )
            dateFormat.timeZone = TimeZone.getTimeZone("GMT")
            return dateFormat.format(this)
        }

    @RequiresApi(Build.VERSION_CODES.N)
    @Throws(ParseException::class)
    fun convertToNewFormat(dateStr: String): String {
        val utc = TimeZone.getTimeZone("UTC")
        val sourceFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val destFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        sourceFormat.timeZone = utc
        val convertedDate = sourceFormat.parse(dateStr)
        return destFormat.format(convertedDate)
    }
}