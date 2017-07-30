package eijenson.braveflontiercarendar

import eijenson.braveflontiercarendar.repository.ScrapingManager
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val a = ScrapingManager()
        println(a.getHtml())
        assertEquals(4, 2 + 2)
    }
}
