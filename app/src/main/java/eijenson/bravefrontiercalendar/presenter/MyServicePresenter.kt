package eijenson.bravefrontiercalendar.presenter

import eijenson.bravefrontiercalendar.ui.service.MyService
import eijenson.bravefrontiercalendar.usecase.BraveNewsUseCase

/**
 * Created by kobayashimakoto on 2017/11/14.
 * サービスクラスのプレゼンター
 * TODO:設計や命名をちゃんとする
 */
class MyServicePresenter(private val service: MyService) {
    private val useCase = BraveNewsUseCase()

    fun checkUpdate() {
        val count = useCase.update()
        if (count > 0) service.notification(count)
    }
}