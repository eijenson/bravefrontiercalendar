package eijenson.bravefrontiercalendar.module

import android.content.Context
import eijenson.bravefrontiercalendar.Application

/**
 * Created by eijenson on 2017/08/27.
 * Applicationクラスの依存性を管理するクラス
 */
//@Module
class ApplicationModule(private val application: Application) {

    //@Provides
    //@Singleton
    fun provideApplicationContext(): Context {
        return application
    }
}