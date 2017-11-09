package eijenson.bravefrontiercalendar

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import eijenson.bravefrontiercalendar.repository.orma.BraveNewsRepositoryImpl
import eijenson.bravefrontiercalendar.usecase.BraveNewsUseCase
import eijenson.bravefrontiercalendar.view.MainActivity
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch


/**
 * Created by eijenson on 2017/09/10.
 * 開発用の処理をまとめたクラス
 */
object DevUtils {
    fun clear() = launch(UI) {
        async(CommonPool) {
            try {
                val bnRepository = BraveNewsRepositoryImpl()
                bnRepository.deleteAll()
            } catch (e: Exception) {
                Log.d("EventListPresenter", "getHtmlAsync", e)
                throw e
            }
        }.await()
    }

    fun dev() {
        BraveNewsUseCase().devDelete()
    }

    fun update() {
        BraveNewsUseCase().update()
    }

    fun notification(context: Context) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                    // アプリでユニークな ID
                    "channel_1",
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
            manager.createNotificationChannel(channel)
        }


        val mBuilder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(context, "channel_1")
        } else {
            //TODO:ここが指摘される
            Notification.Builder(context)
        }

        mBuilder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
        // Creates an explicit intent for an Activity in your app
        val resultIntent = Intent(context, MainActivity::class.java)

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        val stackBuilder = TaskStackBuilder.create(context)
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity::class.java)
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent)
        val resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        )
        mBuilder.setContentIntent(resultPendingIntent)
        // mId allows you to update the notification later on.
        manager.notify(0, mBuilder.build())
    }
}