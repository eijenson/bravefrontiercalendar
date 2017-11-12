package eijenson.bravefrontiercalendar

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import eijenson.bravefrontiercalendar.repository.models.BraveNews
import eijenson.bravefrontiercalendar.repository.orma.BraveNewsRepositoryImpl
import eijenson.bravefrontiercalendar.repository.scraping.RegexUtil
import eijenson.bravefrontiercalendar.usecase.BraveNewsUseCase
import eijenson.bravefrontiercalendar.view.MainActivity
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import java.util.*


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
            @Suppress("DEPRECATION")
            Notification.Builder(context)
        }

        mBuilder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
        val resultIntent = Intent(context, MainActivity::class.java)

        val stackBuilder = TaskStackBuilder.create(context)
        stackBuilder.addParentStack(MainActivity::class.java)
        stackBuilder.addNextIntent(resultIntent)
        val resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        )
        mBuilder.setContentIntent(resultPendingIntent)
        manager.notify(0, mBuilder.build())
    }

    fun insert() {
        val today = Calendar.getInstance().time
        val tomorrow = Calendar.getInstance().let {
            it.add(Calendar.DATE, 1)
            it.time
        }
        val period = RegexUtil.formatDateTime(today) + " ~ " + RegexUtil.formatDateTime(tomorrow)
        val timeList = RegexUtil.dateTime(period)
        val braveNews = BraveNews(title = "テストタイトル" + (Math.random() * 10000).toInt(),
                detail = "テスト詳細" + (Math.random() * 10000).toInt(),
                period = period,
                url = "https://google.co.jp/",
                startTime = timeList?.first(),
                endTime = timeList?.last())
        BraveNewsRepositoryImpl().insert(braveNews)
    }
}