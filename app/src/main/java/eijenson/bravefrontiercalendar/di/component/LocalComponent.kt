package eijenson.bravefrontiercalendar.di.component

import dagger.Component
import eijenson.bravefrontiercalendar.di.module.ApplicationModule
import eijenson.bravefrontiercalendar.di.module.LocalModule
import eijenson.bravefrontiercalendar.usecase.LocalDataUseCase
import javax.inject.Singleton

/**
 * Created by kobayashimakoto on 2017/11/23.
 */
@Singleton
@Component(modules = arrayOf(LocalModule::class, ApplicationModule::class))
interface LocalComponent {
    fun inject(useCase: LocalDataUseCase)
}