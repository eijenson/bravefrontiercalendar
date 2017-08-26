package eijenson.braveflontiercarendar.component

import dagger.Component
import eijenson.braveflontiercarendar.module.ApplicationModule
import javax.inject.Singleton

/**
 * Created by eijenson on 2017/08/27.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface AppComponent {
    fun inject()
}