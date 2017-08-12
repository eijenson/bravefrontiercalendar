package eijenson.braveflontiercarendar.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import eijenson.braveflontiercarendar.R
import eijenson.braveflontiercarendar.repository.scraping.ScrapingManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch


class MainActivity : AppCompatActivity() {

    var html : String = ""
    val scraping = ScrapingManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hoge()
    }

    fun hoge() = launch(UI){
        html = scraping.getHtmlAsync().await()
        hello.text = html
    }

}
