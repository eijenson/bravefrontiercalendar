package eijenson.braveflontiercarendar.presenter

import android.content.Context
import eijenson.braveflontiercarendar.usecase.BraveNewsUseCase
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async

/**
 * メイン画面のUIと画面遷移以外のことをする
 */
class MainPresenter(context: Context) {

    val usecase = BraveNewsUseCase(context)

    fun getHtmlAsync() = async(CommonPool) {
        return@async usecase.getHtml()
    }
}