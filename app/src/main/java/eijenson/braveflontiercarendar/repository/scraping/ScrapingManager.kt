package eijenson.braveflontiercarendar.repository.scraping

import android.content.Context
import android.util.Log
import eijenson.braveflontiercarendar.repository.models.BraveNews
import eijenson.braveflontiercarendar.repository.orma.BraveNewsRepository

/**
 * Created by kobayashimakoto on 2017/07/31.
 */
class ScrapingManager {

    val news = BraveNewsScraping()
    val braveNewsList =
            news.getTitleList().zip(news.getUrlList()).map {
                val newsDetail = BraveNewsDetailScraping(it.second)
                Log.d("ScrapingManager","start sleep")
                Thread.sleep(1000)
                Log.d("ScrapingManager","stop sleep")
                BraveNews(title = it.first,
                        detail = newsDetail.report,
                        period = newsDetail.getReportPeriod(),
                        url = it.second)
            }


    fun getHtml(): String {
        val result = braveNewsList.map { "${it.title}\n${it.period}" }
        return result.joinToString(separator = "\n\n")
    }

    fun insertDatabase(context: Context) {
        BraveNewsRepository(context).insert(braveNewsList)
    }
}