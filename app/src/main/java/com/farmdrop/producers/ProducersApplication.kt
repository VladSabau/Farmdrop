package com.farmdrop.producers

import android.app.Application
import com.farmdrop.producers.di.component.ApplicationComponent
import com.farmdrop.producers.di.component.DaggerApplicationComponent

/**
 * Created by Vlad Sabau on 31.03.19.
 */
class ProducersApplication : Application() {
    private val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: ProducersApplication
            private set
    }

    fun getApplicationComponent(): ApplicationComponent = appComponent
}