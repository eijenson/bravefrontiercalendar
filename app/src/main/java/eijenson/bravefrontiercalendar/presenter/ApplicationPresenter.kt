package eijenson.bravefrontiercalendar.presenter

import android.content.Context
import android.os.Build
import eijenson.bravefrontiercalendar.repository.OrmaHolder
import eijenson.bravefrontiercalendar.ui.notification.MyNotificationManager

/**
 * Created by kobayashimakoto on 2017/11/23.
 * アプリケーションクラスのプレゼンター
 *
 * アプリ全体に関わる初期化処理を記述する
 */
class ApplicationPresenter {

    fun onCreate(context: Context) {
        OrmaHolder.initialize(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            MyNotificationManager(context).createNotificationChannel()
        }
    }
}