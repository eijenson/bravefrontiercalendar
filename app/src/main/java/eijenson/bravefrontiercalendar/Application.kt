package eijenson.bravefrontiercalendar

import android.app.Application
import eijenson.bravefrontiercalendar.repository.OrmaHolder
import eijenson.bravefrontiercalendar.ui.notification.MyNotificationManager

/**
 * アプリケーションクラス
 */
class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        OrmaHolder.initialize(this)
        MyNotificationManager(this).createNotificationChannel()
    }
}
