package eijenson.bravefrontiercalendar.repository.repository

import eijenson.bravefrontiercalendar.repository.models.BraveNews

/**
 * Created by eijenson on 2017/08/24.
 * お知らせ情報のレポジトリクラス
 */
interface BraveNewsRepository {
    fun insert(models: Iterable<BraveNews>)

    fun insert(braveNews: BraveNews): Long

    fun select(id: Long): BraveNews?

    fun selectAll(): List<BraveNews>

    fun update(braveNews: BraveNews)

    fun deleteAll()

    fun countAll(): Int

    fun isEmpty(): Boolean

}