package eijenson.braveflontiercarendar.repository.orma

import android.content.Context
import eijenson.braveflontiercarendar.repository.models.BraveNews
import eijenson.braveflontiercarendar.repository.models.OrmaDatabase

/**
 * Created by kobayashimakoto on 2017/07/31.
 */
class BraveNewsRepository(context: Context) {
    internal val database: OrmaDatabase

    init {
        database = OrmaDatabase.builder(context).build()
    }

    fun insert(braveNews: BraveNews): Long {
        return database.insertIntoBraveNews(braveNews)
    }

    fun select(id: Long): BraveNews? {
        val model = database.selectFromBraveNews().idEq(id)
        return model.valueOrNull()
    }

    fun selectAll(): List<BraveNews> {
        return database.selectFromBraveNews().toList()
    }

    fun deleteAll() {
        database.deleteAll()
    }


    override fun toString(): String {
        return selectAll().toString()
    }
}
