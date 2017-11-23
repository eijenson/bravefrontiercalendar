package eijenson.bravefrontiercalendar.di.component

import dagger.Component
import eijenson.bravefrontiercalendar.di.module.InfraModule
import eijenson.bravefrontiercalendar.usecase.BraveNewsUseCase
import javax.inject.Singleton

/**
 * Daggerのインフラ層のコンポーネントクラス
 */
@Singleton
@Component(modules = arrayOf(InfraModule::class))
interface InfraComponent {
    fun inject(useCase: BraveNewsUseCase)
}