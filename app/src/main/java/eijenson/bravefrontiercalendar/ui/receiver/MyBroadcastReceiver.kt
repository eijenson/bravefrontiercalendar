package eijenson.bravefrontiercalendar.ui.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import eijenson.bravefrontiercalendar.ui.service.MyService

/**
 * Created by kobayashimakoto on 2017/11/13.
 * TODO:命名や設計をちゃんと調べる
 */
class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d(this.toString(), "onReceive Start")
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.action)) {
            MyService.startService(context)
        }
    }
}