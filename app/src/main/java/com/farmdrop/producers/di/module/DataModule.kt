package com.farmdrop.producers.di.module

import com.farmdrop.producers.data.repository.LocalData
import com.farmdrop.producers.data.repository.LocalDataImpl
import com.farmdrop.producers.data.repository.RemoteData
import com.farmdrop.producers.data.repository.RemoteDataImpl
import com.farmdrop.producers.di.scope.ActivityScope
import dagger.Binds
import dagger.Module

/**
 * Created by Vlad Sabau on 31.03.19.
 */
@Module
interface DataModule {

    @Binds
    @ActivityScope
    fun bindLocalData(localData: LocalDataImpl): LocalData

    @Binds
    @ActivityScope
    fun bindRemoteData(remoteData: RemoteDataImpl): RemoteData
}