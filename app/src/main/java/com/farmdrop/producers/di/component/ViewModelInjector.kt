package com.farmdrop.producers.di.component

import com.farmdrop.producers.di.module.NetworkModule
import com.farmdrop.producers.ui.producer.ProducersViewModel
import com.farmdrop.producers.ui.producer.adapter.ProducerViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 * Created by Vlad Sabau on 23.03.19.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified ProducersViewModel.
     * @param producersViewModel ProducersViewModel in which to inject the dependencies
     */
    fun inject(producersViewModel: ProducersViewModel)
    /**
     * Injects required dependencies into the specified ProducerViewModel.
     * @param producerViewModel ProducerViewModel in which to inject the dependencies
     */
    fun inject(producerViewModel: ProducerViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}