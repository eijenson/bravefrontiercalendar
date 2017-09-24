package eijenson.bravefrontiercalendar.di.component

import dagger.Component
import eijenson.bravefrontiercalendar.module.InfraModule
import eijenson.bravefrontiercalendar.usecase.BraveNewsUseCase
import javax.inject.Singleton

/**
 * Daggerのインフラ層のコンポネントクラス
 */
@Singleton
@Component(modules = arrayOf(InfraModule::class))
interface InfraComponent {
    fun inject(useCase: BraveNewsUseCase)
}