package nz.co.test.base.util

import java.math.BigDecimal
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.EnumSet.of

class Extensions {

    companion object {

        fun BigDecimal.getPercentage(percentageVal: Int): BigDecimal {
            return if (this.compareTo(BigDecimal.ZERO) == 0) BigDecimal.ZERO
            else this.multiply(percentageVal.toBigDecimal().divide(100.toBigDecimal()))
        }

        fun BigDecimal.getPrice(): String {
            val format: NumberFormat = NumberFormat.getCurrencyInstance()
            format.maximumFractionDigits = 2
            format.currency = Currency.getInstance("NZD")

            return format.format(this)
        }

        fun LocalDateTime.getFormattedDateStr(dateFormatter: String): String {
            val dateFormat = DateTimeFormatter.ofPattern(dateFormatter)
            return dateFormat.format(this)
        }

        fun String.getFormattedDate(dateFormatter: String): LocalDateTime {
            val dateFormat = DateTimeFormatter.ofPattern(dateFormatter)
            return LocalDateTime.parse(this, dateFormat)
        }
    }
}