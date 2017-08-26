package eijenson.braveflontiercarendar.repository.orma

import eijenson.braveflontiercarendar.repository.OrmaHolder
import eijenson.braveflontiercarendar.repository.models.BraveNews
import eijenson.braveflontiercarendar.repository.models.OrmaDatabase
import eijenson.braveflontiercarendar.repository.repository.BraveNewsRepository
import javax.inject.Inject

/**
 * ゲームのお知らせ情報のデータベースクラス
 */
class BraveNewsRepositoryImpl @Inject constructor() : BraveNewsRepository {
    val database: OrmaDatabase = OrmaHolder.ORMA

    override fun insert(models: Iterable<BraveNews>) {
        database.prepareInsertIntoBraveNews().executeAll(models)
    }

    override fun insert(braveNews: BraveNews): Long {
        return database.insertIntoBraveNews(braveNews)
    }

    override fun select(id: Long): BraveNews? {
        val model = database.selectFromBraveNews().idEq(id)
        return model.valueOrNull()
    }

    override fun selectAll(): List<BraveNews> {
        return database.selectFromBraveNews().orderBy("startTime is null asc").orderByStartTimeAsc().toList()
    }

    override fun update(braveNews: BraveNews) {
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

    override fun deleteAll() {
        database.deleteAll()
    }

    override fun countAll(): Int {
        return database.selectFromBraveNews().count()
    }

    override fun isEmpty(): Boolean {
        return database.selectFromBraveNews().isEmpty
    }
}
