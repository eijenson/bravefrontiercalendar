package eijenson.bravefrontiercalendar.repository.local

import eijenson.bravefrontiercalendar.extensions.truncationTime
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by eijenson on 2017/08/18.
 * カレンダーを取得するレポジトリ
 */
class CalendarRepository(date: Date) {

    private val WEEK_NUM = 7
    private val c = Calendar.getInstance()

    init {
        c.time = date
        c.truncationTime()
    }

    fun getCalendar(): List<Date> {
        c.set(Calendar.DATE, 1)
        val month = c.get(Calendar.MONTH)
        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        val list = ArrayList<Date>()
        do {
            list.addAll(getWeek())
        } while (c.get(Calendar.MONTH) == month)
        return list
    }

    private fun getWeek(): List<Date> {
        val list = ArrayList<Date>()
        for (i in 1..WEEK_NUM) {
            list.add(c.time)
            c.add(Calendar.DATE, 1)
        }
        return list
    }

}