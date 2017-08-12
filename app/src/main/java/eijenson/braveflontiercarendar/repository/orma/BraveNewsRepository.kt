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

    fun insert(braveNews: BraveNews) {
        database.insertIntoBraveNews(braveNews)
    }

    fun select(): List<BraveNews> {
        return database.selectFromBraveNews().toList()
    }
}
