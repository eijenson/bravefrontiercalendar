package eijenson.braveflontiercarendar

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import eijenson.braveflontiercarendar.repository.models.BraveNews
import eijenson.braveflontiercarendar.repository.orma.BraveNewsRepository
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        BraveNewsRepository(appContext).insert(BraveNews(title = "aaaa", detail = "bbbb", period = ""))
        Log.d("orma_table",BraveNewsRepository(appContext).select().toString())
        assertEquals("eijenson.braveflontiercarendar", appContext.packageName)
    }
}
