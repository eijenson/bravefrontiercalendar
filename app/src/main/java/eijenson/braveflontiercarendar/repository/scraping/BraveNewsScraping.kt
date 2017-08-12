package eijenson.braveflontiercarendar.repository.scraping

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Node
import org.jsoup.select.Elements

/**
 * Created by kobayashimakoto on 2017/07/31.
 */
class BraveNewsScraping(private val url: String = "http://www.a-lim.jp/brave/news/news_list.html") {

    private val document: Document by lazy {
        Jsoup.connect(url).get()
    }

    private val body: Elements by lazy {
        document.select(".news > .newslist > a")
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

    private fun Node.href(): String {
        return this.attr("href")
    }
}