package eijenson.bravefrontiercalendar.presenter

import android.content.Context
import eijenson.bravefrontiercalendar.ui.service.MyService
import eijenson.bravefrontiercalendar.usecase.BraveNewsUseCase
import eijenson.bravefrontiercalendar.usecase.LocalDataUseCase
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async

/**
 * Created by kobayashimakoto on 2017/11/23.
 * メイン画面のプレゼンター
 *
 * UIと画面遷移以外のことをする
 */
class MainPresenter {
    private val localDataUseCase = LocalDataUseCase()
    private val braveNewsUseCase = BraveNewsUseCase()

    fun onCreate(context: Context) {
        if (localDataUseCase.isFirstStart()) {
            async(CommonPool) {
                braveNewsUseCase.init()
                localDataUseCase.finishFirstStart()
                MyService.startService(context)
            }
        } else {
            MyService.startService(context)
        }
    }
}