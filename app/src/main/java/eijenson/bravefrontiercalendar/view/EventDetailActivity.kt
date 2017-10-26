package eijenson.bravefrontiercalendar.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.trello.rxlifecycle2.components.RxActivity
import eijenson.bravefrontiercalendar.R
import kotlinx.android.synthetic.main.activity_event_detail.*

/**
 * Created by eijenson on 2017/09/24.
 * イベント詳細画面
 */
class EventDetailActivity : RxActivity() {

    companion object {
        private val TEXT = "text"
        fun createIntent(context: Context, text: String): Intent {
            val intent = Intent(context, EventDetailActivity::class.java)
            intent.putExtra(TEXT, text)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)
        actionBar.setDisplayHomeAsUpEnabled(true)
        val text = intent.getStringExtra(TEXT)
        tv_text.text = text
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, 0)
    }

}