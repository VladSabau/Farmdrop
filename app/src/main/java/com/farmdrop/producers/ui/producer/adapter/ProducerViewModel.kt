package com.farmdrop.producers.ui.producer.adapter

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.farmdrop.producers.data.model.Image
import com.farmdrop.producers.data.model.Producer

class ProducerViewModel : ViewModel() {
    private val name = MutableLiveData<String>()
    private val description = MutableLiveData<String>()
    private val images = MutableLiveData<List<Image>>()

    fun bind(producer: Producer) {
        name.value = producer.name
        description.value = producer.short_description
        images.value = producer.images
    }

    fun getName(): MutableLiveData<String> {
        return name
    }

    fun getDescription(): MutableLiveData<String> {
        return description
    }

    fun getImages(): MutableLiveData<List<Image>> {
        return images
    }
}