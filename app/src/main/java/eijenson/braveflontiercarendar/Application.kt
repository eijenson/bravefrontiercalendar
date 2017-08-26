package eijenson.braveflontiercarendar

import android.app.Application
import eijenson.braveflontiercarendar.repository.OrmaHolder

/**
 * アプリケーションクラス
 */
class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        OrmaHolder.initialize(this)
    }
}
