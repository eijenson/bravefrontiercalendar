package eijenson.braveflontiercarendar.presenter

import android.content.Context
import eijenson.braveflontiercarendar.repository.scraping.ScrapingManager
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async

/**
 * メイン画面のUIと画面遷移以外のことをする
 */
class MainPresenter {

    fun getHtmlAsync(context: Context) = async(CommonPool) {
        val scraping = ScrapingManager(context)
        return@async scraping.getHtml()
    }
}