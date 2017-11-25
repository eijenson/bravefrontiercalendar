package eijenson.bravefrontiercalendar.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import eijenson.bravefrontiercalendar.repository.repository.LocalRepository
import eijenson.bravefrontiercalendar.repository.sharedpreferences.LocalRepositoryImpl
import javax.inject.Singleton

/**
 * Created by kobayashimakoto on 2017/11/23.
 * 端末ごとに保存するクラスの依存性を管理するクラス
 */
@Module
class LocalModule(val context: Context) {
    @Provides
    @Singleton
    fun provideLocalRepository(): LocalRepository = LocalRepositoryImpl(context)
}