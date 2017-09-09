package eijenson.braveflontiercarendar.usecase

import eijenson.braveflontiercarendar.di.component.DaggerInfraComponent
import eijenson.braveflontiercarendar.repository.repository.BraveNewsRepository
import eijenson.braveflontiercarendar.repository.scraping.RegexUtil
import javax.inject.Inject

/**
 * お知らせ情報系のロジックを実装するクラス
 */
class BraveNewsUseCase() {
    @Inject lateinit var repository: BraveNewsRepository

    init{
        DaggerInfraComponent.builder().build().inject(this)
    }

    fun getHtml(): String {
        if (shouldScraping()) {
            repository.insert(ScrapingUseCase().startScraping())
        }
        val braveNewsList = repository.selectAll()
        val result = braveNewsList.map { "${it.title}\n${RegexUtil.formatDateTime(it.startTime)}\n${RegexUtil.formatDateTime(it.endTime)}" }
        return result.joinToString(separator = "\n\n")
    }

    /**
     * スクレイピングをする必要があるのかを返す
     */
    private fun shouldScraping():Boolean{
        return repository.isEmpty()
    }
}