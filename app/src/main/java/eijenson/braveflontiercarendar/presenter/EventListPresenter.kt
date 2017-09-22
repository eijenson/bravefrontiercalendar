package eijenson.braveflontiercarendar.presenter

import android.util.Log
import eijenson.braveflontiercarendar.message.RxBus
import eijenson.braveflontiercarendar.usecase.BraveNewsUseCase
import eijenson.braveflontiercarendar.view.fragment.EventListFragment
import kotlinx.coroutines.experimental.CancellationException
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import java.io.IOException

/**
 * メイン画面のUIと画面遷移以外のことをする
 */
class EventListPresenter(var eventListFragment: EventListFragment?) {

    val usecase = BraveNewsUseCase()

    fun setHtml() = launch(UI) {
        try {
            eventListFragment?.showProgressBar()
            val result = getHtmlAsync().await()
            RxBus.send(result)
        } catch (e: CancellationException) {
            Log.e("EventListPresenter", "setHtml")
            e.printStackTrace()
            eventListFragment?.showToast("canceled")
        } catch (e: IOException) {
            e.printStackTrace()
            eventListFragment?.showToast("通信に失敗しました")
        } catch (e: Exception) {
            Log.e("EventListPresenter", "setHtml")
            e.printStackTrace()
            eventListFragment?.showToast("exception")
        } finally {
            eventListFragment?.hideProgressBar()
        }
    }

    fun onDestroy() {
        eventListFragment = null
    }


    fun getHtmlAsync() = async(CommonPool) {
        try {
            return@async usecase.getHtml()
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}