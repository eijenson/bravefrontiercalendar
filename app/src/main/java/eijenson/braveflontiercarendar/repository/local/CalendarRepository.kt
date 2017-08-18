package eijenson.braveflontiercarendar.repository.local

import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by eijenson on 2017/08/18.
 */
class CalendarRepository {

    private val WEEK_NUM = 7
    private val c = Calendar.getInstance()

    fun getFirstOneWeek(): List<Date> {
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1)
        val weekOfYear = c.get(Calendar.WEEK_OF_YEAR)
        val c2 = Calendar.getInstance()
        c2.set(Calendar.WEEK_OF_YEAR, weekOfYear)
        c2.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        val list = ArrayList<Date>()
        for (i in 1..WEEK_NUM) {
            list.add(c2.time)
            c2.add(Calendar.DATE, 1)
        }
        return list
    }

    fun getFirstOneWeek(c3: Calendar): List<Date> {
        val weekOfYear = c3.get(Calendar.WEEK_OF_YEAR)
        val c2 = Calendar.getInstance()
        c2.set(Calendar.WEEK_OF_YEAR, weekOfYear)
        c2.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        val list = ArrayList<Date>()
        for (i in 1..WEEK_NUM) {
            list.add(c2.time)
            c2.add(Calendar.DATE, 1)
        }
        return list
    }


    fun getCalendar(): List<Date> {
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1)
        val month = c.get(Calendar.MONTH)
        val weekOfYear = c.get(Calendar.WEEK_OF_YEAR)
        val c2 = Calendar.getInstance()
        c2.set(Calendar.WEEK_OF_YEAR, weekOfYear)
        c2.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        val list = ArrayList<Date>()
        do {
            val dateList = CalendarRepository().getFirstOneWeek(c2)
            list.addAll(dateList)
            c2.add(Calendar.DATE, WEEK_NUM)
        } while (c2.get(Calendar.MONTH) == month)
        return list
    }

}