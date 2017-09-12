package eijenson.braveflontiercarendar.usecase

import android.util.Log
import eijenson.braveflontiercarendar.message.RxBus
import eijenson.braveflontiercarendar.repository.models.BraveNews
import eijenson.braveflontiercarendar.repository.scraping.BraveNewsDetailScraping
import eijenson.braveflontiercarendar.repository.scraping.BraveNewsScraping
import eijenson.braveflontiercarendar.repository.scraping.RegexUtil

/**
 * スクレイピングを指示するクラス
 */
class ScrapingUseCase() {
    fun startScraping(): List<BraveNews> {
        val news = BraveNewsScraping()
        RxBus.send(news.getTitleList().size.toString())
        return news.getTitleList().zip(news.getUrlList()).mapIndexed { index, it ->
            val newsDetail = BraveNewsDetailScraping(it.second)
            Log.d("ScrapingManager", "start sleep")
            Thread.sleep(1000)
            Log.d("ScrapingManager", "stop sleep")
            val timeList = RegexUtil.dateTime(newsDetail.period)
            RxBus.send(index + 1)
            BraveNews(title = it.first,
                    detail = newsDetail.report,
                    period = newsDetail.period,
                    url = it.second,
                    startTime = timeList?.first(),
                    endTime = timeList?.last())
        }
    }
}