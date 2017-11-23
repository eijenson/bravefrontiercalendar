package eijenson.bravefrontiercalendar.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import eijenson.bravefrontiercalendar.Application
import javax.inject.Singleton

/**
 * Created by eijenson on 2017/08/27.
 * Applicationクラスの依存性を管理するクラス
 */
@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return application.applicationContext
    }
}