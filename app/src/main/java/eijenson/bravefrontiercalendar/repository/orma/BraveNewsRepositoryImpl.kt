package eijenson.bravefrontiercalendar.repository.orma

import eijenson.bravefrontiercalendar.repository.OrmaHolder
import eijenson.bravefrontiercalendar.repository.models.BraveNews
import eijenson.bravefrontiercalendar.repository.models.OrmaDatabase
import eijenson.bravefrontiercalendar.repository.repository.BraveNewsRepository
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

    override fun selectWhereUrl(url: String): BraveNews? {
        val model = database.selectFromBraveNews().urlEq(url)
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

    override fun updateIsViewingSiteToTrue(id: Long) {
        database.updateBraveNews().idEq(id)
                .isViewingSite(true)
                .execute()
    }

    override fun updateAllIsViewingSiteToFalse() {
        database.updateBraveNews().isViewingSiteEq(true)
                .isViewingSite(false)
                .execute()
    }

    override fun delete(id: Long) {
        database.deleteFromBraveNews().idEq(id).execute()
    }

    override fun deleteAll() {
        database.deleteAll()
    }

    override fun count(): Int {
        return database.selectFromBraveNews().count()
    }

    override fun isEmpty(): Boolean {
        return database.selectFromBraveNews().isEmpty
    }
}
