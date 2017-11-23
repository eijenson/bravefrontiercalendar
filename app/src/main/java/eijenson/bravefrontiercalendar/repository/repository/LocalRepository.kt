package eijenson.bravefrontiercalendar.repository.repository

import eijenson.bravefrontiercalendar.model.LocalData

/**
 * Created by kobayashimakoto on 2017/11/23.
 * 端末内データを保存するレポジトリ
 */
interface LocalRepository {
    fun get(): LocalData

    fun save(localData: LocalData)

    fun deleteAll()

}