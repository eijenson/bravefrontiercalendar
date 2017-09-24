package eijenson.bravefrontiercalendar.repository.scraping

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
    val period = RegexUtil.period(report)
}