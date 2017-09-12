package eijenson.braveflontiercarendar.presenter

import android.util.Log
import eijenson.braveflontiercarendar.usecase.BraveNewsUseCase
import eijenson.braveflontiercarendar.view.fragment.EventListFragment
import kotlinx.coroutines.experimental.CancellationException
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

/**
 * メイン画面のUIと画面遷移以外のことをする
 */
class EventListPresenter(val eventListFragment: EventListFragment) {

    val usecase = BraveNewsUseCase()

    fun setHtml() = launch(UI) {
        try {
            eventListFragment.showProgressBar()
            val text = getHtmlAsync().await()
            eventListFragment.setText(text)
        } catch (e: CancellationException) {
            eventListFragment.showToast("canceled")
        } catch (e: Exception) {
            eventListFragment.showToast("exception")
        } finally {
            eventListFragment.hideProgressBar()
        }
    }


    fun getHtmlAsync() = async(CommonPool) {
        try {
            return@async usecase.getHtml()
        } catch (e: Exception) {
            Log.d("EventListPresenter", "getHtmlAsync", e)
            throw e
        }
    }
}