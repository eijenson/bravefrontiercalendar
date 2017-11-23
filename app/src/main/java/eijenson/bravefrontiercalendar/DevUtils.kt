package eijenson.bravefrontiercalendar

import android.content.Context
import android.util.Log
import eijenson.bravefrontiercalendar.model.BraveNews
import eijenson.bravefrontiercalendar.repository.orma.BraveNewsRepositoryImpl
import eijenson.bravefrontiercalendar.repository.scraping.RegexUtil
import eijenson.bravefrontiercalendar.repository.sharedpreferences.LocalRepositoryImpl
import eijenson.bravefrontiercalendar.ui.notification.MyNotificationManager
import eijenson.bravefrontiercalendar.usecase.BraveNewsUseCase
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import java.util.*


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

    fun dev() {
        BraveNewsUseCase().devDelete()
    }

    fun update() {
        BraveNewsUseCase().update()
    }

    fun notification(context: Context) {
        MyNotificationManager(context).showNotification("タイトル", "詳細")
    }

    fun insert() {
        val today = Calendar.getInstance().time
        val tomorrow = Calendar.getInstance().let {
            it.add(Calendar.DATE, 1)
            it.time
        }
        val period = RegexUtil.formatDateTime(today) + " ~ " + RegexUtil.formatDateTime(tomorrow)
        val timeList = RegexUtil.dateTime(period)
        val braveNews = BraveNews(title = "テストタイトル" + (Math.random() * 10000).toInt(),
                detail = "テスト詳細" + (Math.random() * 10000).toInt(),
                period = period,
                url = "https://google.co.jp/",
                startTime = timeList?.first(),
                endTime = timeList?.last())
        BraveNewsRepositoryImpl().insert(braveNews)
    }

    fun showText(context: Context) = LocalRepositoryImpl(context).get().toString()
}