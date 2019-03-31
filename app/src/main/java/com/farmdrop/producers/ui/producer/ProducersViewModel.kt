package com.farmdrop.producers.ui.producer

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.farmdrop.producers.BaseViewModel
import com.farmdrop.producers.R
import com.farmdrop.producers.data.Producer
import com.farmdrop.producers.data.database.ProducersDao
import com.farmdrop.producers.network.ProducersApi
import com.farmdrop.producers.ui.producer.adapter.ProducersAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class ProducersViewModel(private val producersDao: ProducersDao) : BaseViewModel() {

    @Inject
    lateinit var producersApi: ProducersApi

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

    private fun streamProducers(page: Int, perPageLimit: Int): Observable<List<Producer>>? {
        return fetchProducers(page, perPageLimit)
            .onErrorResumeNext(Observable.fromCallable { producersDao.all })
            .filter(Predicate { list -> !list.isEmpty() })
    }

    private fun fetchProducers(
        page: Int,
        perPageLimit: Int
    ): Observable<List<Producer>> {
        return producersApi.getProducers(page, perPageLimit)
            .concatMap { producersList ->
                producersDao.insertAll(producersList.response)
                Observable.just(producersList.response)
            }
    }

    fun loadProducers(page: Int, perPageLimit: Int) {
        subscription = streamProducers(page, perPageLimit)
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