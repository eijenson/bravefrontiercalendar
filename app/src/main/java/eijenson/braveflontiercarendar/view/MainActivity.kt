package eijenson.braveflontiercarendar.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import eijenson.braveflontiercarendar.R
import eijenson.braveflontiercarendar.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

/**
 * メインアクティビティ
 */
class MainActivity : AppCompatActivity() {

    val presenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setHtml()
    }

    fun setHtml() = launch(UI) {
        val text = presenter.getHtmlAsync(context = this@MainActivity).await()
        hello.text = text
    }
}
