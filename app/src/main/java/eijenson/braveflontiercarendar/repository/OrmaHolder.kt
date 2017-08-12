package eijenson.braveflontiercarendar.repository

import android.content.Context
import eijenson.braveflontiercarendar.repository.models.OrmaDatabase

/**
 * データベースのハンドラークラス
 */
object OrmaHolder {
    lateinit var ORMA: OrmaDatabase

    fun initialize(context: Context) {
        ORMA = OrmaDatabase.builder(context).build()
    }
}