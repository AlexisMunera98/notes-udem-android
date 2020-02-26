package co.edu.udem.myapplication

import android.content.Context
import android.text.format.DateUtils
import java.util.*

class TimeUtils {

    companion object{
        fun formatShortDate(
            context: Context?,
            dateInMillis: Long
        ): String? {
            val sb = StringBuilder()
            val formatter = Formatter(sb)
            return DateUtils.formatDateRange(
                context, formatter, dateInMillis, dateInMillis,
                DateUtils.FORMAT_ABBREV_ALL or DateUtils.FORMAT_NO_YEAR,
                getDisplayTimeZone(context)?.getID()
            ).toString()
        }

        fun getDisplayTimeZone(context: Context?): TimeZone? {
            val defaultTz = TimeZone.getDefault()
            return if (defaultTz != null) defaultTz else TimeZone.getTimeZone(
                "UTC"
            )
        }
    }
}