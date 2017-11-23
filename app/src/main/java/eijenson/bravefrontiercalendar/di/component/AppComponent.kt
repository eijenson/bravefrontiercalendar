package eijenson.bravefrontiercalendar.di.component

import dagger.Component
import eijenson.bravefrontiercalendar.di.module.ApplicationModule
import eijenson.bravefrontiercalendar.repository.sharedpreferences.LocalRepositoryImpl
import javax.inject.Singleton

/**
 * Created by eijenson on 2017/08/27.
 * DaggerのContextを提供するコンポーネントクラス
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface AppComponent {
    fun inject(repositoryImpl: LocalRepositoryImpl)
}