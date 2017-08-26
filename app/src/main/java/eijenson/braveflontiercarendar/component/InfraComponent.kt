package eijenson.braveflontiercarendar.component

import dagger.Component
import eijenson.braveflontiercarendar.module.InfraModule
import eijenson.braveflontiercarendar.usecase.BraveNewsUseCase
import javax.inject.Singleton

/**
 * Created by eijenson on 2017/08/24.
 */
@Singleton
@Component(modules = arrayOf(InfraModule::class))
interface InfraComponent {
    fun inject(useCase: BraveNewsUseCase)
}