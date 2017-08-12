package eijenson.braveflontiercarendar.repository.scraping

import org.jsoup.Jsoup

/**
 * お知らせ詳細画面のスクレイピングを行うクラス
 */
class BraveNewsDetailScraping(val url: String) {

    val report: String by lazy {
        val document = Jsoup.connect(url).get()
        val iframeUrl = document.getElementsByTag("iframe").attr("src")
        Jsoup.connect(iframeUrl).get()
                .getElementsByClass("reportTxt")
                .text()
    }

    private val date = """\d{4}年\d{1,2}月\d{1,2}日\([月火水木金土日]\) \d{1,2}:\d{1,2}:\d{1,2}"""
    private val period = """$date[\s\S]*～.?$date"""

    fun getReportPeriod(): String? {
        val regex = Regex(period)
        return regex.find(report)?.value
    }

}