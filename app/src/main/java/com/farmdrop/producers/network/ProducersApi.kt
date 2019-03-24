package com.farmdrop.producers.network

import com.farmdrop.producers.data.ProducersList
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 * Created by Vlad Sabau on 23.03.19.
 */
interface ProducersApi {

    @GET("producers")
    fun getProducers(): Observable<ProducersList>
}