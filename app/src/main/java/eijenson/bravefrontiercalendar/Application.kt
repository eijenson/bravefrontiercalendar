package eijenson.bravefrontiercalendar

import android.app.Application
import eijenson.bravefrontiercalendar.di.component.DaggerLocalComponent
import eijenson.bravefrontiercalendar.di.component.LocalComponent
import eijenson.bravefrontiercalendar.di.module.LocalModule
import eijenson.bravefrontiercalendar.presenter.ApplicationPresenter

/**
 * アプリケーションクラス
 */
class Application : Application() {
    private val presenter = ApplicationPresenter()

    companion object {
        lateinit var localComponent: LocalComponent
    }

    override fun onCreate() {
        super.onCreate()
        presenter.onCreate(this)

        localComponent = DaggerLocalComponent.builder().localModule(LocalModule(this)).build()
    }
}
