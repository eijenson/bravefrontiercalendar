package eijenson.braveflontiercarendar

import eijenson.braveflontiercarendar.extensions.getDate
import eijenson.braveflontiercarendar.extensions.getMonth
import eijenson.braveflontiercarendar.extensions.getYear
import eijenson.braveflontiercarendar.repository.local.CalendarRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.text.DateFormat
import java.util.*

/**
 * Created by eijenson on 2017/08/18.
 */
class CalendarRepositoryTest {

    lateinit var c: Calendar

    @Before
    fun before() {
        c = Calendar.getInstance()
    }

    @Test
    fun getCalendarTest() {
        val list = CalendarRepository().getCalendar()
        list.map {
            println(it)
        }

        Assert.assertEquals(c.time.month, Date().month)
    }

    @Test
    fun getCalendarTest2() {
        val date = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.JAPAN).parse("2016/8/16")
        val list = CalendarRepository(date).getCalendar()
        list.map {
            p(it)
        }

        Assert.assertEquals(c.time.month, Date().month)
    }

    fun p(date: Date) {
        val c = Calendar.getInstance()
        c.time = date
        println("" + c.getYear() + "/" + (c.getMonth() + 1) + "/" + c.getDate())
    }

    fun p(c: Calendar) {
        print("Date:")
        print(c.get(Calendar.YEAR))
        print("/")
        print(c.get(Calendar.MONTH) + 1)
        print("/")
        println(c.get(Calendar.DATE))
    }

}