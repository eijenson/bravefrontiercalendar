package eijenson.bravefrontiercalendar.repository

import android.content.Context
import eijenson.bravefrontiercalendar.repository.models.OrmaDatabase

/**
 * データベースのハンドラークラス
 */
object OrmaHolder {
    lateinit var ORMA: OrmaDatabase

    fun initialize(context: Context) {
        ORMA = OrmaDatabase.builder(context).build()
    }
}