package eijenson.bravefrontiercalendar.ui.notification

import android.app.*
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import eijenson.bravefrontiercalendar.R
import eijenson.bravefrontiercalendar.ui.view.activity.MainActivity

/**
 * Created by kobayashimakoto on 2017/11/12.
 * TODO:命名や設計をちゃんと調べる
 */
class MyNotificationManager(private val context: Context) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    companion object {
        const val CHANNEL_ID = "channel_1"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel() {
        val channel = NotificationChannel(
                // アプリでユニークな ID
                CHANNEL_ID,
                // ユーザが「設定」アプリで見ることになるチャンネル名
                context.getString(R.string.channel_name),
                // チャンネルの重要度（0 ~ 4）
                NotificationManager.IMPORTANCE_DEFAULT
        )
        // 通知時のライトの色
        channel.lightColor = Color.GREEN
        // ロック画面で通知を表示するかどうか
        channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE

        // 端末にチャンネルを登録し、「設定」で見れるようにする
        notificationManager.createNotificationChannel(channel)
    }

    fun showNotification(title: String, text: String) {
        val mBuilder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(context, CHANNEL_ID)
        } else {
            @Suppress("DEPRECATION")
            Notification.Builder(context)
        }

        mBuilder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(text)

        val stackBuilder = TaskStackBuilder.create(context)
        stackBuilder.addParentStack(MainActivity::class.java)
        stackBuilder.addNextIntent(MainActivity.createIntent(context))
        val resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        )
        mBuilder.setContentIntent(resultPendingIntent)
        notificationManager.notify(0, mBuilder.build())
    }
}