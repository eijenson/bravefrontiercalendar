package eijenson.braveflontiercarendar

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import eijenson.braveflontiercarendar.repository.models.BraveNews
import eijenson.braveflontiercarendar.repository.orma.BraveNewsRepositoryImpl
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
    lateinit var repository: BraveNewsRepositoryImpl

    @Before
    fun before() {
        context = InstrumentationRegistry.getTargetContext()
        repository = BraveNewsRepositoryImpl(context, null)
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
        Assert.assertEquals("url", lastData?.url)
    }

    @Test
    fun 検索で0件の場合はnull() {
        val nullData = repository.select(-1)
        Assert.assertEquals(null, nullData)
    }

    @Test
    fun 正常更新() {
        // APIから取得できるようになったら追加
    }

    fun データ数が正しいか() {
        // APIから取得できるようになったら追加
    }

    @Test
    fun devUpdate() {
        val textList = repository.database.selectFromBraveNews().periodIsNotNull().toList()
        textList.map {
            val list = RegexUtil.dateTime(it.period)
            it.startTime = list?.first()
            it.endTime = list?.last()
            repository.update(it)
        }
        repository.selectAll().map {
            Log.d("test", it.startTimeJapan+"")
        }
    }

    @Test
    fun test() {
        val textList = repository.database.selectFromBraveNews().periodIsNotNull().toList()
        textList.map {
            val list = RegexUtil.dateTime(it.period!!)
            println(it.title + " " + list)
        }
    }

    val BraveNews.startTimeJapan: String?
        get() = RegexUtil.formatDateTime(startTime)

}
