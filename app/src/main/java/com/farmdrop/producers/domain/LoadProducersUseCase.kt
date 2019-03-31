package com.farmdrop.producers.domain

import com.farmdrop.producers.data.model.Producer
import com.farmdrop.producers.data.repository.ProducersRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Vlad Sabau on 31.03.19.
 */
class LoadProducersUseCase @Inject constructor(private val repository: ProducersRepository) {
    fun loadProducers(page: Int, perPageLimit: Int): Observable<List<Producer>>? {
        return repository.loadProducers(page, perPageLimit)
    }
}