package eijenson.braveflontiercarendar.repository

import android.content.Context
import eijenson.braveflontiercarendar.repository.models.OrmaDatabase

/**
 * Created by kobayashimakoto on 2017/07/31.
 */
object OrmaHolder {
    lateinit var ORMA: OrmaDatabase

    fun initialize(context: Context) {
        ORMA = OrmaDatabase.builder(context).build()
    }
}