package com.farmdrop.producers.di.component

import com.farmdrop.producers.di.module.DataModule
import com.farmdrop.producers.di.module.DomainModule
import com.farmdrop.producers.di.scope.ActivityScope
import com.farmdrop.producers.ui.producer.ProducersActivity
import dagger.Subcomponent

/**
 * Created by Vlad Sabau on 31.03.19.
 */
@Subcomponent(modules = [DataModule::class, DomainModule::class])
@ActivityScope
interface ActivityComponent {
    fun inject(activity: ProducersActivity)
}