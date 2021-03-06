package com.farmdrop.producers.data.repository

import com.farmdrop.producers.data.model.Producer
import io.reactivex.Observable

/**
 * Created by Vlad Sabau on 31.03.19.
 */
interface RemoteData {
    fun fetchProducers(
        page: Int,
        perPageLimit: Int
    ): Observable<List<Producer>>
}
