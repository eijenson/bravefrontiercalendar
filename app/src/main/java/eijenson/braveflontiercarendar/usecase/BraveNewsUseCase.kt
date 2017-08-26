package eijenson.braveflontiercarendar.usecase

import android.util.Log
import eijenson.braveflontiercarendar.repository.repository.BraveNewsRepository
import eijenson.braveflontiercarendar.repository.scraping.RegexUtil
import eijenson.braveflontiercarendar.repository.scraping.ScrapingManager
import javax.inject.Inject

/**
 * お知らせ情報系のロジックを実装するクラス
 */
class BraveNewsUseCase() {
    @Inject lateinit var repository: BraveNewsRepository

    fun getHtml(): String {
        Log.d("UseCase","Start")
        if (repository.isEmpty()) {
            repository.insert(ScrapingManager().startScraping())
        }
        val braveNewsList = repository.selectAll()
        val result = braveNewsList.map { "${it.title}\n${RegexUtil.formatDateTime(it.startTime)}\n${RegexUtil.formatDateTime(it.endTime)}" }
        return result.joinToString(separator = "\n\n")
        Log.d("UseCase","End")
    }
}