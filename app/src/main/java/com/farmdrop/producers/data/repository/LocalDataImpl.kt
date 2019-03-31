package com.farmdrop.producers.data.repository

import com.farmdrop.producers.data.database.ProducersDao
import com.farmdrop.producers.data.model.Producer
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Vlad Sabau on 31.03.19.
 */
class LocalDataImpl @Inject constructor(private val producersDao: ProducersDao) :
    LocalData {

    override fun getProducers(): Observable<List<Producer>> {
        return Observable.fromCallable { producersDao.all }
    }

    override fun insertProducers(producersList: List<Producer>) {
        producersDao.insertAll(producersList)
    }
}