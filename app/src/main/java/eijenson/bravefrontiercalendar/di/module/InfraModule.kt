package eijenson.bravefrontiercalendar.di.module

import dagger.Module
import dagger.Provides
import eijenson.bravefrontiercalendar.repository.orma.BraveNewsRepositoryImpl
import eijenson.bravefrontiercalendar.repository.repository.BraveNewsRepository
import javax.inject.Singleton

/**
 * お知らせ情報系のDBを依存性を管理するクラス
 */
@Module
class InfraModule {
    @Provides
    @Singleton
    fun provideBraveNewsRepository(): BraveNewsRepository = BraveNewsRepositoryImpl()
}