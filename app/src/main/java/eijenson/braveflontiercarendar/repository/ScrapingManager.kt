package eijenson.braveflontiercarendar.repository

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async

/**
 * Created by kobayashimakoto on 2017/07/31.
 */
class ScrapingManager {

    fun getHtmlAsync() = async(CommonPool) {
        return@async getHtml()
    }

    fun getHtml(): String {
        val news = BraveNewsScraping()
        val period = news.getUrlList().map {
            BraveNewsDetailScraping(it).getReportPeriod()
        }
        val result = news.getTitleList().zip(period, { t, p -> "$t\n$p" })
        return result.joinToString(separator = "\n\n")
    }

}