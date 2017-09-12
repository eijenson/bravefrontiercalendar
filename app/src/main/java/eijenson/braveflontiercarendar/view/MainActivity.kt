package eijenson.braveflontiercarendar.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import eijenson.braveflontiercarendar.R
import eijenson.braveflontiercarendar.message.RxBus
import eijenson.braveflontiercarendar.presenter.MainPresenter
import eijenson.braveflontiercarendar.view.fragment.DevelopFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

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
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.calendar -> {
                    println("aaa")
                }
                R.id.list -> {
                    println("bbb")
                }
                R.id.dev -> {
                    val fragment = DevelopFragment.newInstance()
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_content, fragment)
                            .commit()
                }
                else -> {
                    println("xxx")
                }
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        RxBus.listen<Int>().subscribe {
            println(it)
            changeProgressPercent(it)
            setPersentText(progress_bar_loading.progress.toString() + "/" + progress_bar_loading.max)
        }
        RxBus.listen<String>().subscribe {
            println(it)
            setProgressMax(it.toInt())
            setPersentText(progress_bar_loading.progress.toString() + "/" + progress_bar_loading.max)
        }
    }

    fun setPersentText(text: String) = launch(UI) {
        progress_bar_persent.text = text

    }

    fun setText(text: String) {
        hello.text = text
    }

    fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    fun showProgressBar() {
        progress_bar_loading.visibility = View.VISIBLE
    }

    fun changeProgressPercent(percent: Int) {
        progress_bar_loading.progress = percent
    }

    fun setProgressMax(max: Int) {
        progress_bar_loading.max = max
    }

    fun hideProgressBar() {
        //progress_bar_loading.visibility = View.INVISIBLE
    }
}
