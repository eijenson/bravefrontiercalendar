package eijenson.bravefrontiercalendar.ui.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


/**
 * Created by kobayashimakoto on 2017/11/13.
 * TODO:命名や設計をちゃんと調べる
 */
class MyService : Service() {
    private val observable = Observable.interval(0, 1, TimeUnit.SECONDS)
    private val subscribe by lazy {
        observable.subscribe { i ->
            run(i)
        }
    }

    companion object {
        fun startService(context: Context) {
            context.startService(Intent(context, MyService::class.java))
        }
    }

    private fun run(i: Long) {
        Log.d("AAA", "interval : " + i)
    }

    override fun onCreate() {
        super.onCreate()
        subscribe
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        subscribe.dispose()
    }
}