package eijenson.braveflontiercarendar.module

import dagger.Module
import dagger.Provides
import eijenson.braveflontiercarendar.repository.orma.BraveNewsRepositoryImpl
import eijenson.braveflontiercarendar.repository.repository.BraveNewsRepository
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