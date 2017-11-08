package eijenson.bravefrontiercalendar.usecase

import android.util.Log
import eijenson.bravefrontiercalendar.di.component.DaggerInfraComponent
import eijenson.bravefrontiercalendar.repository.models.BraveNews
import eijenson.bravefrontiercalendar.repository.repository.BraveNewsRepository
import java.util.concurrent.locks.ReentrantLock
import javax.inject.Inject
import kotlin.concurrent.withLock

/**
 * お知らせ情報系のロジックを実装するクラス
 */
class BraveNewsUseCase {
    @Inject lateinit var repository: BraveNewsRepository
    val scrapingUseCase = ScrapingUseCase()

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
        return repository.selectAll()
    }

    fun update() {
        val localList = repository.selectAll().map { it.url }
        val serverList = scrapingUseCase.getList()
        serverList.forEach {
            if (!localList.contains(it.second)) {
                repository.insert(scrapingUseCase.getBraveNews(it.first, it.second))
            }
        }
    }

    fun devDelete() {
        Log.d("", "" + repository.countAll())
        repository.delete(repository.selectAll().first().id)
        Log.d("", "" + repository.countAll())
    }

    /**
     * スクレイピングをする必要があるのかを返す
     */
    fun hasBraveNews(): Boolean {
        return !repository.isEmpty()
    }
}