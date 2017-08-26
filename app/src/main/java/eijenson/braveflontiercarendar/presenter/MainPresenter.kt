package eijenson.braveflontiercarendar.presenter

import eijenson.braveflontiercarendar.usecase.BraveNewsUseCase
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async

/**
 * メイン画面のUIと画面遷移以外のことをする
 */
class MainPresenter() {

    val usecase = BraveNewsUseCase()

    fun getHtmlAsync() = async(CommonPool) {
        return@async usecase.getHtml()
    }
}