package com.farmdrop.producers.di.module

import com.farmdrop.producers.BuildConfig
import com.farmdrop.producers.data.network.ProducersApi
import com.farmdrop.producers.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Module which provides all required dependencies about network
 * Created by Vlad Sabau on 23.03.19.
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
class NetworkModule {
    /**
     * Provides the Producer service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Producer service implementation.
     */
    @Provides
    @ApplicationScope
    internal fun provideProducersApi(retrofit: Retrofit): ProducersApi {
        return retrofit.create(ProducersApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @ApplicationScope
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.ENDPOINT_BASE)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }
}