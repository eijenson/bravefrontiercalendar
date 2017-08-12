package eijenson.braveflontiercarendar.repository.scraping

import android.content.Context
import android.util.Log
import eijenson.braveflontiercarendar.repository.models.BraveNews
import eijenson.braveflontiercarendar.repository.orma.BraveNewsRepository

/**
 * Created by kobayashimakoto on 2017/07/31.
 */
class ScrapingManager(context: Context) {

    lateinit var news: BraveNewsScraping
    lateinit var braveNewsList: List<BraveNews>

    init {
        val repository = BraveNewsRepository(context)
        if (repository.count() == 0) {
            news = BraveNewsScraping()
            braveNewsList =
                    news.getTitleList().zip(news.getUrlList()).map {
                        val newsDetail = BraveNewsDetailScraping(it.second)
                        Log.d("ScrapingManager", "start sleep")
                        Thread.sleep(1000)
                        Log.d("ScrapingManager", "stop sleep")
                        BraveNews(title = it.first,
                                detail = newsDetail.report,
                                period = newsDetail.getReportPeriod(),
                                url = it.second)
                    }
            repository.insert(braveNewsList)
        } else {
            braveNewsList = repository.selectAll()
        }
    }


    fun getHtml(): String {
        val result = braveNewsList.map { "${it.title}\n${it.period}" }
        return result.joinToString(separator = "\n\n")
    }
}