package com.farmdrop.producers.data.repository

import com.farmdrop.producers.data.model.Producer
import io.reactivex.Observable
import io.reactivex.functions.Predicate
import javax.inject.Inject

/**
 * Created by Vlad Sabau on 31.03.19.
 */
class ProducersRepositoryImpl @Inject constructor(private val localData: LocalData,
                                                  private val remoteData: RemoteData
): ProducersRepository {
    override fun changeUserEmail(page: Int, perPageLimit: Int): Observable<List<Producer>>? {
        return streamProducers(page, perPageLimit)
    }

    private fun streamProducers(page: Int, perPageLimit: Int): Observable<List<Producer>>? {
        return fetchProducers(page, perPageLimit)
            .onErrorResumeNext(localData.getProducers())
            .filter(Predicate { list -> !list.isEmpty() })
    }

    private fun fetchProducers(
        page: Int,
        perPageLimit: Int
    ): Observable<List<Producer>> {
        return remoteData.fetchProducers(page, perPageLimit)
            .concatMap { producersList ->
                localData.insertProducers(producersList)
                Observable.just(producersList)
            }
    }
}