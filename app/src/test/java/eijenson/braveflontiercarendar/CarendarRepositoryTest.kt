package eijenson.braveflontiercarendar

import eijenson.braveflontiercarendar.repository.local.CarendarRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * Created by eijenson on 2017/08/18.
 */
class CarendarRepositoryTest {

    lateinit var c: Calendar

    @Before
    fun before() {
        c = Calendar.getInstance()
    }

    @Test
    fun getFirstOneWeekTest() {
        val list = CarendarRepository().getFirstOneWeek()
        list.map {
            println(it)
        }

        Assert.assertEquals(c.time.month, Date().month)
    }

    @Test
    fun getCalendarTest() {

        val list = CarendarRepository().getCalendar()
        list.map {
            println(it)
        }

        Assert.assertEquals(c.time.month, Date().month)
    }

    fun p(c: Calendar) {
        print("Date:")
        print(c.get(Calendar.YEAR))
        print("/")
        print(c.get(Calendar.MONTH)+1)
        print("/")
        println(c.get(Calendar.DATE))
    }

}