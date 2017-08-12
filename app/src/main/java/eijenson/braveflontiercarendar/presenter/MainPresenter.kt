package eijenson.braveflontiercarendar.presenter

import android.content.Context
import eijenson.braveflontiercarendar.repository.scraping.ScrapingManager
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async

/**
 * Created by kobayashimakoto on 2017/08/12.
 */
class MainPresenter {

    fun getHtmlAsync(context: Context) = async(CommonPool) {
        val scraping = ScrapingManager(context)
        return@async scraping.getHtml()
    }
}