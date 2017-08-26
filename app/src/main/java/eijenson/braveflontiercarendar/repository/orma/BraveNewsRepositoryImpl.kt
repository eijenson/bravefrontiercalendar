package eijenson.braveflontiercarendar.repository.orma

import eijenson.braveflontiercarendar.Application
import eijenson.braveflontiercarendar.repository.models.BraveNews
import eijenson.braveflontiercarendar.repository.models.OrmaDatabase

/**
 * ゲームのお知らせ情報のデータベースクラス
 */
class BraveNewsRepository() {
    val database: OrmaDatabase = OrmaDatabase.builder(Application.context).build()

    fun insert(models: Iterable<BraveNews>) {
        database.prepareInsertIntoBraveNews().executeAll(models)
    }

    fun insert(braveNews: BraveNews): Long {
        return database.insertIntoBraveNews(braveNews)
    }

    fun select(id: Long): BraveNews? {
        val model = database.selectFromBraveNews().idEq(id)
        return model.valueOrNull()
    }

    fun selectAll(): List<BraveNews> {
        return database.selectFromBraveNews().orderBy("startTime is null asc").orderByStartTimeAsc().toList()
    }

    fun update(braveNews: BraveNews) {
        braveNews.apply {
            database.updateBraveNews().idEq(id)
                    .title(title)
                    .detail(detail)
                    .period(period)
                    .url(url)
                    .startTime(startTime)
                    .endTime(endTime)
                    .execute()
        }
    }

    fun deleteAll() {
        database.deleteAll()
    }

    fun countAll(): Int {
        return database.selectFromBraveNews().count()
    }

    fun isEmpty(): Boolean {
        return database.selectFromBraveNews().isEmpty
    }
}
