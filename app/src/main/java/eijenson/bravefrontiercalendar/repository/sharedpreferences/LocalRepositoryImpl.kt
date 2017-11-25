package eijenson.bravefrontiercalendar.repository.sharedpreferences

import android.content.Context
import android.content.Context.MODE_PRIVATE
import eijenson.bravefrontiercalendar.model.LocalData
import eijenson.bravefrontiercalendar.repository.repository.LocalRepository

/**
 * Created by kobayashimakoto on 2017/11/23.
 * 端末内データを保存するレポジトリクラス
 * SharedPreference依存
 */
class LocalRepositoryImpl(val context: Context) : LocalRepository {

    companion object {
        private const val IS_FIRST_START_KEY = "is_first_start"
    }

    private val fileName = "local_data"
    private val preference = context.getSharedPreferences(fileName, MODE_PRIVATE)

    override fun get(): LocalData = LocalData(preference.getBoolean(IS_FIRST_START_KEY, true))

    override fun save(localData: LocalData) {
        val editor = preference.edit()
        editor.putBoolean(IS_FIRST_START_KEY, localData.isFirstStart)
        editor.apply()
    }

    override fun deleteAll() {
        val editor = preference.edit()
        editor.clear().apply()
    }
}