package com.farmdrop.producers.di.module

import android.app.Application
import android.arch.persistence.room.Room
import com.farmdrop.producers.data.database.AppDatabase
import com.farmdrop.producers.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

/**
 * Created by Vlad Sabau on 31.03.19.
 */
@Module
@Suppress("unused")
class DatabaseModule {

    @Provides
    @ApplicationScope
    fun provideProducersDb(application: Application) =
        Room.databaseBuilder(
            application,
            AppDatabase::class.java, "db-producers"
        ).build()

    @Provides
    @ApplicationScope
    fun provideProducersDao(appDatabase: AppDatabase) = appDatabase.producersDao()
}