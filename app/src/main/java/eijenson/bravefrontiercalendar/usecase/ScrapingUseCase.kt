package eijenson.bravefrontiercalendar.usecase

import android.util.Log
import eijenson.bravefrontiercalendar.message.RxBus
import eijenson.bravefrontiercalendar.repository.models.BraveNews
import eijenson.bravefrontiercalendar.repository.models.Progress
import eijenson.bravefrontiercalendar.repository.scraping.BraveNewsDetailScraping
import eijenson.bravefrontiercalendar.repository.scraping.BraveNewsScraping
import eijenson.bravefrontiercalendar.repository.scraping.RegexUtil

/**
 * スクレイピングを指示するクラス
 */
class ScrapingUseCase {
    fun startScraping(): List<BraveNews> {
        val news = BraveNewsScraping()
        RxBus.send(Progress(0, news.getTitleList().size))
        return news.getTitleList().zip(news.getUrlList()).mapIndexed { index, it ->
            val newsDetail = BraveNewsDetailScraping(it.second)
            Log.d("ScrapingManager", "start sleep")
            Thread.sleep(1000)
            Log.d("ScrapingManager", "stop sleep")
            val timeList = RegexUtil.dateTime(newsDetail.period)
            RxBus.send(Progress(index + 1, news.getTitleList().size))
            BraveNews(title = it.first,
                    detail = newsDetail.report,
                    period = newsDetail.period,
                    url = it.second,
                    startTime = timeList?.first(),
                    endTime = timeList?.last())
        }
    }
}