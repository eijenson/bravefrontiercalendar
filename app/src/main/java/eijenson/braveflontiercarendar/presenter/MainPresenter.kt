package eijenson.braveflontiercarendar.presenter

import android.content.Context
import eijenson.braveflontiercarendar.usecase.BraveNewsUseCase
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async

/**
 * メイン画面のUIと画面遷移以外のことをする
 */
class MainPresenter(context: Context) {

    val useCase = BraveNewsUseCase(context)

    fun getHtmlAsync() = async(CommonPool) {
        return@async useCase.getHtml()
    }
}