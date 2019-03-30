package com.farmdrop.producers.network

import com.farmdrop.producers.data.ProducersList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The interface which provides methods to get result of webservices
 * Created by Vlad Sabau on 23.03.19.
 */
interface ProducersApi {

    @GET("producers")
    fun getProducers(
        @Query("page") page: Int,
        @Query("per_page_limit") perPageLimit: Int
    ): Observable<ProducersList>
}