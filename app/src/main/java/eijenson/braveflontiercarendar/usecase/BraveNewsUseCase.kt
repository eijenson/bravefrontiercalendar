package eijenson.braveflontiercarendar.usecase

import eijenson.braveflontiercarendar.di.component.DaggerInfraComponent
import eijenson.braveflontiercarendar.repository.models.BraveNews
import eijenson.braveflontiercarendar.repository.repository.BraveNewsRepository
import java.util.concurrent.locks.ReentrantLock
import javax.inject.Inject
import kotlin.concurrent.withLock

/**
 * お知らせ情報系のロジックを実装するクラス
 */
class BraveNewsUseCase() {
    @Inject lateinit var repository: BraveNewsRepository

    object Singleton {
        val lock = ReentrantLock()
    }

    init {
        DaggerInfraComponent.builder().build().inject(this)
    }

    fun getHtml(): List<BraveNews> {
        Singleton.lock.withLock {
            if (shouldScraping()) {
                repository.insert(ScrapingUseCase().startScraping())
            }
        }
        return repository.selectAll()
    }

    /**
     * スクレイピングをする必要があるのかを返す
     */
    private fun shouldScraping(): Boolean {
        return repository.isEmpty()
    }
}