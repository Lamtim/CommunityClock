package com.example.tim.communityclock.utils

import java.text.SimpleDateFormat
import java.util.*


class DateUtils {

    companion object {

        fun getDateNowToString(simpleDateFormat: SimpleDateFormat): String{
            val now = Date()
            return simpleDateFormat.format(now)
        }

        fun getRandomDate(): Date{

            val cal = Calendar.getInstance()

            var minYear = 2018
            var minMonth = 0
            var minDayOfMonth = 1
            var minHour = 0
            var minMin = 0
            var minSecond = 0

            val year = randBetween(minYear, cal.get(Calendar.YEAR))// Here you can set Range of years you need
            if(year == 2018){
                minMonth = 10
            }
            var month = randBetween(minMonth, cal.get(Calendar.MONTH))
            val gc = GregorianCalendar(year, month, 1)
            if(year == 2018 && month == 10){
                minDayOfMonth = 26
            }
            var day = randBetween(minDayOfMonth, 31)

            if(year == 2018 && month == 10 && minDayOfMonth == 26){
                minHour = 16
                minMin = 5
                minSecond = 0
            }
            val hour = randBetween(minHour, 23)
            val min = randBetween(minMin, 59)
            val sec = randBetween(minSecond, 59)

            gc.set(year, month, day, hour, min, sec)

            return  gc.time

        }


        fun randBetween(start: Int, end: Int): Int {
            return start + Math.round(Math.random() * (end - start)).toInt()
        }

    }
}