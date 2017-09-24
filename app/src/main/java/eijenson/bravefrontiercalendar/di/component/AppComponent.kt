package eijenson.bravefrontiercalendar.di.component

/**
 * Created by eijenson on 2017/08/27.
 * DaggerのContextを提供するコンポーネントクラス
 */
//@Singleton
//@Component(modules = arrayOf(ApplicationModule::class))
interface AppComponent {
    fun inject()
}