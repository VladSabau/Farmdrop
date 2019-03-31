package com.farmdrop.producers.di.module

import com.farmdrop.producers.data.repository.ProducersRepository
import com.farmdrop.producers.data.repository.ProducersRepositoryImpl
import com.farmdrop.producers.di.scope.ActivityScope
import dagger.Binds
import dagger.Module

/**
 * Created by Vlad Sabau on 31.03.19.
 */
@Module
interface DomainModule {

    @Binds
    @ActivityScope
    fun bindRepository(producersRepositoryImpl: ProducersRepositoryImpl): ProducersRepository
}