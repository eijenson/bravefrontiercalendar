package eijenson.braveflontiercarendar.component

import dagger.Component
import eijenson.braveflontiercarendar.module.InfraModule
import eijenson.braveflontiercarendar.usecase.BraveNewsUseCase
import javax.inject.Singleton

/**
 * Daggerのインフラ層のコンポネントクラス
 */
@Singleton
@Component(modules = arrayOf(InfraModule::class))
interface InfraComponent {
    fun inject(useCase: BraveNewsUseCase)
}