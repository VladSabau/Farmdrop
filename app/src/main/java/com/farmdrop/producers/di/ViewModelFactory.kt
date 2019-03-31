package com.farmdrop.producers.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.farmdrop.producers.domain.LoadProducersUseCase
import com.farmdrop.producers.ui.producer.ProducersViewModel
import javax.inject.Inject

/**
 * Created by Vlad Sabau on 23.03.19.
 */
class ViewModelFactory @Inject constructor(private val loadProducersUseCase: LoadProducersUseCase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProducersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProducersViewModel(loadProducersUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}