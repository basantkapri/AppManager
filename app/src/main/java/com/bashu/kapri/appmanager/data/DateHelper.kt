package com.bashu.kapri.appmanager.data

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    private const val fallFormatHome = "EEEE, MMMM dd yyyy hh:mm a"
    private const val INSTALLED_ON = "EE, MMM. dd yyyy hh:mm aa"
    private const val DATE_FORMAT = "dd/MM/yyyy"
    private const val DATE_FORMAT1 = "dd-MM-yyyy"

    fun getFormattedDate(milliseconds: Long): String {
        val formatter = SimpleDateFormat(INSTALLED_ON)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliseconds
        return formatter.format(calendar.time)
    }
}