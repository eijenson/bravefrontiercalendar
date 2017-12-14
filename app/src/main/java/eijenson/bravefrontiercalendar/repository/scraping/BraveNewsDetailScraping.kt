package eijenson.bravefrontiercalendar.repository.scraping

import org.jsoup.Jsoup

/**
 * お知らせ詳細画面のスクレイピングを行うクラス
 */
class BraveNewsDetailScraping(val url: String) {
    val urlHost = "https://webnotice.ssl.brave.a-lim.jp/news/"
    val urlHost2 = "https://webnotice.ssl.brave.a-lim.jp"

    val report: String by lazy {
        val document = Jsoup.connect(urlHost + url).get()
        val iframeUrl = document.getElementsByTag("iframe").attr("src")
        Jsoup.connect(urlHost2 + iframeUrl).get()
                .getElementsByClass("reportTxt")
                .text()
    }
    val period = RegexUtil.period(report)
}