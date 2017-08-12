package eijenson.braveflontiercarendar

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import eijenson.braveflontiercarendar.repository.models.BraveNews
import eijenson.braveflontiercarendar.repository.orma.BraveNewsRepository
import eijenson.braveflontiercarendar.repository.scraping.RegexUtil
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    lateinit var context: Context
    lateinit var repository: BraveNewsRepository

    @Before
    fun before() {
        context = InstrumentationRegistry.getTargetContext()
        repository = BraveNewsRepository(context)
        //repository.deleteAll()
    }

    @Test
    fun 正常登録() {
        val id = repository.insert(BraveNews(title = "title", detail = "detail", period = "period", url = "url"))
        Assert.assertNotEquals(-1, id)
        val lastData = repository.select(id)
        Assert.assertEquals("title", lastData?.title)
        Assert.assertEquals("detail", lastData?.detail)
        Assert.assertEquals("period", lastData?.period)
    }

    @Test
    fun テスト() {
        val id = repository.insert(BraveNews(title = "title", detail = "detail", period = "period", url = "url"))
        val lastData = repository.select(id)
        Assert.assertEquals("title", lastData?.title)
    }

    @Test
    fun 検索で0件の場合はnull() {
        val nullData = repository.select(-1)
        Assert.assertEquals(null, nullData)
    }

    @Test
    fun test2() {
    }

    @Test
    fun test() {
        val list = repository.selectAll()
        list.map {
            it.period = RegexUtil.getReportPeriod(it.detail)
            repository.update(it)
        }
    }
}
