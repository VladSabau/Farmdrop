package com.farmdrop.producers.data.repository

import com.farmdrop.producers.BaseTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

/**
 * Created by Vlad Sabau on 31.03.19.
 */
class PhotoCreditRepositoryTest : BaseTest() {

    private val localData = mockk<LocalData>(relaxed = true)
    private val remoteData = mockk<RemoteData>(relaxed = true)

    var repository: ProducersRepositoryImpl = ProducersRepositoryImpl(localData, remoteData)

    @Before
    fun before() {
        repository = ProducersRepositoryImpl(localData, remoteData)
    }

    @Test
    fun makeRetrofitCallThrowError() {
        val throwable = Throwable()
        every { remoteData.fetchProducers(1, 10) } returns Observable.error(throwable)
        every { localData.getProducers() } returns Observable.error(throwable)

        repository.loadProducers(1, 10)?.test()?.assertError(throwable)
    }

    @Test
    fun `when retrofit call failed get data from db`() {
        val producers = getMockProducersList()
        every { remoteData.fetchProducers(1, 10) } returns Observable.error(Throwable())
        every { localData.getProducers() } returns Observable.just(producers)

        repository.loadProducers(1, 10)?.test()?.assertValue(producers)
    }

    @Test
    fun `when retrofit call success insert in db`() {
        val producers = getMockProducersList()
        every { remoteData.fetchProducers(1, 10) } returns Observable.just(producers)

        repository.loadProducers(1, 10)?.test()?.assertValue(producers)

        verify { localData.insertProducers(producers) }
    }
}
