package com.farmdrop.producers.ui.producer

import com.farmdrop.producers.BaseTest
import com.farmdrop.producers.data.model.Producer
import com.farmdrop.producers.domain.LoadProducersUseCase
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test


/**
 * Created by Vlad Sabau on 01.04.19.
 */
class ProducersViewModelTest : BaseTest() {

    private val loadProducersUseCase = mockk<LoadProducersUseCase>(relaxed = true)

    var viewModel: ProducersViewModel = ProducersViewModel(loadProducersUseCase)

    @Before
    fun before() {
        viewModel = ProducersViewModel(loadProducersUseCase)
    }

    @Test
    fun retrieveProducers() {
        val producers = getMockProducersList()
        every { loadProducersUseCase.loadProducers(1, 10) } returns Observable.just(producers)

        val testObserver = TestObserver<List<Producer>>()
        loadProducersUseCase.loadProducers(1, 10)?.subscribe(testObserver)

        testObserver.assertNoErrors()
        testObserver.assertValue(producers)
    }

    @Test
    fun `when producers cant be retrieved show error`() {
        val throwable = Throwable()
        every { loadProducersUseCase.loadProducers(1, 10) } returns Observable.error(throwable)

        val testObserver = TestObserver<List<Producer>>()
        loadProducersUseCase.loadProducers(1, 10)?.subscribe(testObserver)

        testObserver.assertError(throwable)
    }
}