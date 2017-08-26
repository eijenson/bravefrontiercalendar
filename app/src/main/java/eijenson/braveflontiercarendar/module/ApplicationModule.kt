package eijenson.braveflontiercarendar.module

import android.content.Context
import eijenson.braveflontiercarendar.Application

/**
 * Created by eijenson on 2017/08/27.
 */
//@Module
class ApplicationModule(private val application: Application) {

    //@Provides
    //@Singleton
    fun provideApplicationContext(): Context {
        return application
    }
}