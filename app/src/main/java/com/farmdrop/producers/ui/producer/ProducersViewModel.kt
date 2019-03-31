package com.farmdrop.producers.ui.producer

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import com.farmdrop.producers.R
import com.farmdrop.producers.data.model.Producer
import com.farmdrop.producers.domain.LoadProducersUseCase
import com.farmdrop.producers.ui.producer.adapter.ProducersAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class ProducersViewModel @Inject constructor(private val loadProducersUseCase: LoadProducersUseCase) : ViewModel() {

    val producersAdapter: ProducersAdapter =
        ProducersAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadProducers(1, 10) }

    private lateinit var subscription: Disposable

    init {
        loadProducers(1, 10)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun loadProducers(page: Int, perPageLimit: Int) {
        subscription = loadProducersUseCase.loadProducers(page, perPageLimit)
            ?.singleOrError()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.unsubscribeOn(Schedulers.io())
            ?.doOnSubscribe { onRetrieveProducersStart() }
            ?.doAfterTerminate { onRetrieveProducersFinish() }
            ?.subscribe(
                { result -> onRetrieveProducersSuccess(result) },
                { error -> onRetrieveProducersError(error) }
            )!!
    }

    private fun onRetrieveProducersStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveProducersFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveProducersSuccess(producers: List<Producer>) {
        producersAdapter.updateProducers(producers)
    }

    private fun onRetrieveProducersError(error: Throwable) {
        errorMessage.value = R.string.loading_producers_error
        Timber.e(error)
    }
}