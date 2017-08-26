package eijenson.braveflontiercarendar

import android.app.Application
import android.content.Context
import eijenson.braveflontiercarendar.repository.OrmaHolder

/**
 * アプリケーションクラス
 */
class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        OrmaHolder.initialize(this)
        context = this
    }

    companion object {
        lateinit var context:Context
    }
}
