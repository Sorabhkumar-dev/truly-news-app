package com.sorabh.data.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateUtil {

    @JvmStatic
    fun getLocalDateTimeFormServerDate(date: String): LocalDateTime =
        LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"))

    @JvmStatic
    fun getUserFormat(localDateTime: LocalDateTime): String =
        localDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
}