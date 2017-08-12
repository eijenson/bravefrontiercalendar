package eijenson.braveflontiercarendar

import android.app.Application
import eijenson.braveflontiercarendar.repository.OrmaHolder

/**
 * Created by kobayashimakoto on 2017/08/11.
 */
class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        OrmaHolder.initialize(this)
    }
}
