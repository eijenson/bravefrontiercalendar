package eijenson.braveflontiercarendar.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import eijenson.braveflontiercarendar.DevUtils
import eijenson.braveflontiercarendar.R
import kotlinx.android.synthetic.main.activity_develop.*

class DevelopActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, DevelopActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_develop)
        clear.setOnClickListener {
            DevUtils.clear()
        }
        dev.setOnClickListener {
            DevUtils.dev()
        }

    }
}
