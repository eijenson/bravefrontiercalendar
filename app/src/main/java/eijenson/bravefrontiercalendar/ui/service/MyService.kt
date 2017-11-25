package eijenson.bravefrontiercalendar.ui.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import eijenson.bravefrontiercalendar.BuildConfig
import eijenson.bravefrontiercalendar.presenter.MyServicePresenter
import eijenson.bravefrontiercalendar.ui.notification.MyNotificationManager
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


/**
 * Created by kobayashimakoto on 2017/11/13.
 * TODO:命名や設計をちゃんと調べる
 */
class MyService : Service() {
    private val observable = if (BuildConfig.DEBUG) {
        //Observable.interval(0, 20, TimeUnit.SECONDS)
        Observable.interval(0, 1, TimeUnit.HOURS)
    } else {
        Observable.interval(0, 1, TimeUnit.HOURS)
    }
    private val subscribe by lazy {
        observable.subscribe { i ->
            run(i)
        }
    }
    private lateinit var presenter: MyServicePresenter
    private val notificationManager: MyNotificationManager by lazy {
        MyNotificationManager(this)
    }

    companion object {
        fun startService(context: Context) {
            context.startService(Intent(context, MyService::class.java))
        }
    }

    private fun run(i: Long) {
        Log.d("AAA", "interval : " + i)
        // TODO:初回起動時の初期データ反映時は動かないようにしたい
        presenter.checkUpdate()
    }

    fun notification(count: Long) {
        notificationManager.showNotification("サーバが更新されたよ", "更新数" + count)
    }

    override fun onCreate() {
        super.onCreate()
        presenter = MyServicePresenter(this)
        subscribe
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        subscribe.dispose()
    }
}