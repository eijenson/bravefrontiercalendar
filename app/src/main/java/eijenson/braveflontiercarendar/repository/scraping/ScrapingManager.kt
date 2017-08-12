package eijenson.braveflontiercarendar.repository.scraping

import android.util.Log
import eijenson.braveflontiercarendar.repository.models.BraveNews

/**
 * スクレイピングを指示するクラス
 */
class ScrapingManager() {
    fun startScraping(): List<BraveNews> {
        val news = BraveNewsScraping()
        return news.getTitleList().zip(news.getUrlList()).map {
            val newsDetail = BraveNewsDetailScraping(it.second)
            Log.d("ScrapingManager", "start sleep")
            Thread.sleep(1000)
            Log.d("ScrapingManager", "stop sleep")
            BraveNews(title = it.first,
                    detail = newsDetail.report,
                    period = newsDetail.period,
                    url = it.second)
        }

    }
}