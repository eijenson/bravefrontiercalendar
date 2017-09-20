package eijenson.braveflontiercarendar.usecase

import eijenson.braveflontiercarendar.di.component.DaggerInfraComponent
import eijenson.braveflontiercarendar.repository.models.BraveNews
import eijenson.braveflontiercarendar.repository.repository.BraveNewsRepository
import javax.inject.Inject

/**
 * お知らせ情報系のロジックを実装するクラス
 */
class BraveNewsUseCase() {
    @Inject lateinit var repository: BraveNewsRepository

    init{
        DaggerInfraComponent.builder().build().inject(this)
    }

    fun getHtml(): List<BraveNews> {
        if (shouldScraping()) {
            repository.insert(ScrapingUseCase().startScraping())
        }
        return repository.selectAll()
    }

    /**
     * スクレイピングをする必要があるのかを返す
     */
    private fun shouldScraping():Boolean{
        return repository.isEmpty()
    }
}