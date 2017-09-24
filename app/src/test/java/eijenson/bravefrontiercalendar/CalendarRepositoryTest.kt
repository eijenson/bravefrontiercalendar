package eijenson.bravefrontiercalendar

import eijenson.bravefrontiercalendar.extensions.set
import eijenson.bravefrontiercalendar.repository.local.CalendarRepository
import org.junit.Assert
import org.junit.Test
import java.util.*

/**
 * Created by eijenson on 2017/08/18.
 * カレンダーレポジトリのテスト
 */
class CalendarRepositoryTest {

    @Test
    fun success_1970_1_1_first_date_is_1969_12_28() {
        val cal = Calendar.getInstance()
        cal.set(1970, 0, 1)
        val repository = CalendarRepository(cal.time)

        val list = repository.getCalendar()

        val first = list.first()
        cal.set(1969, 11, 28, 0, 0, 0, 0)
        Assert.assertEquals(first.time, cal.time.time)
    }

    @Test
    fun success_2017_12_31_last_date_is_2018_1_6() {
        val cal = Calendar.getInstance()
        cal.set(2017, 11, 31)
        val repository = CalendarRepository(cal.time)

        val list = repository.getCalendar()

        val first = list.last()
        cal.set(2018, 0, 6, 0, 0, 0, 0)
        Assert.assertEquals(first.time, cal.time.time)
    }
}