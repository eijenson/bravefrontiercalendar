package eijenson.bravefrontiercalendar

import android.util.Log
import eijenson.bravefrontiercalendar.message.RxBus
import eijenson.bravefrontiercalendar.repository.orma.BraveNewsRepositoryImpl
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

/**
 * Created by eijenson on 2017/09/10.
 * 開発用の処理をまとめたクラス
 */
object DevUtils {
    fun clear() = launch(UI) {
        async(CommonPool) {
            try {
                val bnRepository = BraveNewsRepositoryImpl()
                bnRepository.deleteAll()
            } catch (e: Exception) {
                Log.d("EventListPresenter", "getHtmlAsync", e)
                throw e
            }
        }.await()
    }

    private var percent = 0
    fun dev() {
        RxBus.send("10")
        RxBus.send(percent)
        percent++
    }
}