package eijenson.braveflontiercarendar.presenter

import android.util.Log
import eijenson.braveflontiercarendar.usecase.BraveNewsUseCase
import eijenson.braveflontiercarendar.view.MainActivity
import kotlinx.coroutines.experimental.CancellationException
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

/**
 * メイン画面のUIと画面遷移以外のことをする
 */
class MainPresenter(val mainActivity: MainActivity) {

    val usecase = BraveNewsUseCase()

    fun setHtml() = launch(UI) {
        try {
            mainActivity.showProgressBar()
            val text = getHtmlAsync().await()
            mainActivity.setText(text)
        } catch (e: CancellationException) {
            mainActivity.showToast("canceled")
        } catch (e: Exception) {
            mainActivity.showToast("exception")
        } finally {
            mainActivity.hideProgressBar()
        }
    }


    fun getHtmlAsync() = async(CommonPool) {
        try {
            return@async usecase.getHtml()
        } catch (e: Exception) {
            Log.d("MainPresenter", "getHtmlAsync", e)
            throw e
        }
    }
}