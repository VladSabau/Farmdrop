package com.farmdrop.producers.ui.producer.adapter

import android.arch.lifecycle.MutableLiveData
import com.farmdrop.producers.BaseViewModel
import com.farmdrop.producers.data.Producer

class ProducerViewModel : BaseViewModel() {
    private val postName = MutableLiveData<String>()
    private val postDescription = MutableLiveData<String>()

    fun bind(producer: Producer) {
        postName.value = producer.name
        //todo: trim short desc
        postDescription.value = producer.shortDescription
    }

    fun getProducerName(): MutableLiveData<String> {
        return postName
    }

    fun getProducerDescription(): MutableLiveData<String> {
        return postDescription
    }
}