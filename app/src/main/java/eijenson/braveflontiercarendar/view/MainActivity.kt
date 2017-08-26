package eijenson.braveflontiercarendar.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import eijenson.braveflontiercarendar.R
import eijenson.braveflontiercarendar.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * メインアクティビティ
 */
class MainActivity : AppCompatActivity() {

    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this)
        presenter.setHtml()
    }

    fun setText(text: String) {
        hello.text = text
    }

    fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
