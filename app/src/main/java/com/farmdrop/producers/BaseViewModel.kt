package com.farmdrop.producers

import android.arch.lifecycle.ViewModel
import com.farmdrop.producers.di.component.DaggerViewModelInjector
import com.farmdrop.producers.di.component.ViewModelInjector
import com.farmdrop.producers.di.module.NetworkModule
import com.farmdrop.producers.ui.producer.ProducersViewModel
import com.farmdrop.producers.ui.producer.adapter.ProducerViewModel

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is ProducersViewModel -> injector.inject(this)
            is ProducerViewModel -> injector.inject(this)
        }
    }
}