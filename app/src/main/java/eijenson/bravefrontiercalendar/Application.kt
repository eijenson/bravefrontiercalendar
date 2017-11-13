package eijenson.bravefrontiercalendar

import android.app.Application
import android.os.Build
import eijenson.bravefrontiercalendar.repository.OrmaHolder
import eijenson.bravefrontiercalendar.ui.notification.MyNotificationManager
import eijenson.bravefrontiercalendar.ui.service.MyService

/**
 * アプリケーションクラス
 */
class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        OrmaHolder.initialize(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            MyNotificationManager(this).createNotificationChannel()
        }
        MyService.startService(this)
    }
}
