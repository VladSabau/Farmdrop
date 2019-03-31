package com.farmdrop.producers.data.repository

import com.farmdrop.producers.data.model.Producer
import com.farmdrop.producers.data.network.ProducersApi
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Vlad Sabau on 31.03.19.
 */
class RemoteDataImpl @Inject constructor(private val producersApi: ProducersApi) :
    RemoteData {
    override fun fetchProducers(
        page: Int,
        perPageLimit: Int
    ): Observable<List<Producer>> {
        return producersApi.getProducers(page, perPageLimit)
            .concatMap { producersList ->
                Observable.just(producersList.response)
            }
    }
}