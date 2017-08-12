package eijenson.braveflontiercarendar.presenter

import android.content.Context
import eijenson.braveflontiercarendar.repository.scraping.ScrapingManager
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async

/**
 * Created by kobayashimakoto on 2017/08/12.
 */
class MainPresenter {

    lateinit var scraping: ScrapingManager

    fun getHtmlAsync(context: Context) = async(CommonPool) {
        scraping = ScrapingManager()
        insertDatabase(context)
        return@async scraping.getHtml()
    }

    fun insertDatabase(context: Context) {
        scraping = ScrapingManager()
        scraping.insertDatabase(context)
    }
}