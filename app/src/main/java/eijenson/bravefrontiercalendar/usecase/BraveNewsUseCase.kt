package eijenson.bravefrontiercalendar.usecase

import android.util.Log
import eijenson.bravefrontiercalendar.di.component.DaggerInfraComponent
import eijenson.bravefrontiercalendar.repository.models.BraveNews
import eijenson.bravefrontiercalendar.repository.models.BraveNewsHeaderList
import eijenson.bravefrontiercalendar.repository.repository.BraveNewsRepository
import java.util.concurrent.locks.ReentrantLock
import javax.inject.Inject
import kotlin.concurrent.withLock

/**
 * お知らせ情報系のロジックを実装するクラス
 */
class BraveNewsUseCase {
    @Inject lateinit var repository: BraveNewsRepository
    private val scrapingUseCase = ScrapingUseCase()

    object Singleton {
        val lock = ReentrantLock()
    }

    init {
        DaggerInfraComponent.builder().build().inject(this)
    }

    fun getHtml(): List<BraveNews> {
        Singleton.lock.withLock {
            if (!hasBraveNews()) {
                repository.insert(scrapingUseCase.startScraping())
            }
        }
        return repository.selectViewing()
    }

    fun update(): Long {
        val localList = repository.selectViewing()
        val serverList = BraveNewsHeaderList(scrapingUseCase.getHeaderList())

        // ローカルにあってサーバにないデータのフラグを折る
        localList.forEach {
            if (!serverList.contains(it.getHeader())) {
                it.isViewingSite = false
                repository.update(it)
            }
        }

        // サーバからのデータを追加
        var count = 0L
        serverList.forEach {
            val braveNews = repository.selectWhereUrl(it.url)
            if (braveNews == null) {
                repository.insert(scrapingUseCase.getBraveNews(it.title, it.url))
                count++
            }
        }

        return count
    }

    fun devDelete() {
        Log.d("", "" + repository.count())
        repository.delete(repository.selectAll().first().id)
        Log.d("", "" + repository.count())
    }

    /**
     * スクレイピングをする必要があるのかを返す
     */
    fun hasBraveNews(): Boolean {
        return !repository.isEmpty()
    }
}