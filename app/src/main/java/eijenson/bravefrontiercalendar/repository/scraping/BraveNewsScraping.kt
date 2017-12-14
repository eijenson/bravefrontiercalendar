package eijenson.bravefrontiercalendar.repository.scraping

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Node
import org.jsoup.select.Elements

/**
 * お知らせ位一覧画面のスクレイピングを行うクラス
 */
class BraveNewsScraping(private val url: String = "https://webnotice.ssl.brave.a-lim.jp/news/news.html") {

    private val document: Document by lazy {
        Jsoup.connect(url).get()
    }

    private val body: Elements by lazy {
        document.select(".news_flame_mdl > .newslist > a")
    }

    fun getTitleList(): List<String> {
        return body.map {
            it.select(".newslist_1").text()
        }
    }

    fun getUrlList(): List<String> {
        return body.map {
            it.href()
        }
    }

    private fun Node.href(): String = this.attr("href")
}