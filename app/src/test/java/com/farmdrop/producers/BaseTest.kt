package com.farmdrop.producers

import com.farmdrop.producers.data.model.Producer
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Rule

/**
 * Created by Vlad Sabau on 01.04.19.
 */
open class BaseTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @After
    fun afterTests() {
        unmockkAll()
    }

    fun getMockProducersList() : List<Producer> {
        val producersList: ArrayList<Producer> = ArrayList()

        val producer1 = Producer()
        producer1.id = 1

        val producer2 = Producer()
        producer2.id = 2

        val producer3 = Producer()
        producer3.id = 3

        producersList.add(producer1)
        producersList.add(producer2)
        producersList.add(producer3)

        return producersList
    }
}