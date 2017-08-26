package eijenson.braveflontiercarendar.module

import dagger.Module
import dagger.Provides
import eijenson.braveflontiercarendar.repository.orma.BraveNewsRepositoryImpl
import eijenson.braveflontiercarendar.repository.repository.BraveNewsRepository
import javax.inject.Singleton

/**
 * Created by eijenson on 2017/08/24.
 */
@Module
class InfraModule {
    @Provides
    @Singleton
    fun provideBraveNewsRepository(): BraveNewsRepository {
        return BraveNewsRepositoryImpl()
    }
}