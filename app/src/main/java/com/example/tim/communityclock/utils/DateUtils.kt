package com.example.tim.communityclock.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {

    companion object {
        fun getDateNowToString(simpleDateFormat: SimpleDateFormat): String{
            val now = Date()
            return simpleDateFormat.format(now)
        }
    }
}