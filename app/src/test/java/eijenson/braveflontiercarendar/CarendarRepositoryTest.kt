package eijenson.braveflontiercarendar

import eijenson.braveflontiercarendar.repository.local.CarendarRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
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
        val df = SimpleDateFormat("yyyy/MM/dd/EEEE")
        p(c)
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1)
        p(c)
        val weekOfYear = c.get(Calendar.WEEK_OF_YEAR)
        println(weekOfYear)
        val c2 = Calendar.getInstance()
        p(c2)
        c2.set(Calendar.WEEK_OF_YEAR, weekOfYear)
        c2.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        p(c2)
        println(df.format(c.time))
        val list = CarendarRepository().getFirstOneWeek()
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