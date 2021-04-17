package br.com.everis.sovamu.utils

import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

object DateFormatUtil {

    @JvmStatic
    fun getDateFormatted(date: LocalDate): String {
        val formatType = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return formatType.format(date)
    }

    @JvmStatic
    fun brTime(): ZonedDateTime = ZonedDateTime.now(ZoneId.of("Brazil/East"))

}