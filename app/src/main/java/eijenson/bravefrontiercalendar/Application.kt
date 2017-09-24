package eijenson.bravefrontiercalendar

import android.app.Application
import eijenson.bravefrontiercalendar.repository.OrmaHolder

/**
 * アプリケーションクラス
 */
class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        OrmaHolder.initialize(this)
    }
}
