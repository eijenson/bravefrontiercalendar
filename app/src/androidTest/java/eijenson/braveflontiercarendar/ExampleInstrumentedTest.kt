package eijenson.braveflontiercarendar

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import eijenson.braveflontiercarendar.repository.models.BraveNews
import eijenson.braveflontiercarendar.repository.orma.BraveNewsRepository
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
        repository.deleteAll()
    }

    @Test
    fun 正常登録() {
        val id = repository.insert(BraveNews(title = "title", detail = "detail", period = "period"))
        val lastData = repository.select(id)
        Assert.assertEquals("title", lastData?.title)
    }

    @Test
    fun insertTest2() {
        val id = repository.insert(BraveNews(title = "title", detail = "detail", period = "period"))
        val lastData = repository.select(-1)
        Assert.assertEquals(null, lastData?.title)
    }
}
