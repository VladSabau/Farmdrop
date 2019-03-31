package com.farmdrop.producers.di.component

import android.app.Application
import com.farmdrop.producers.di.module.DatabaseModule
import com.farmdrop.producers.di.module.NetworkModule
import com.farmdrop.producers.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component

/**
 * Created by Vlad Sabau on 31.03.19.
 */
@Component(modules = [DatabaseModule::class, NetworkModule::class])
@ApplicationScope
interface ApplicationComponent{
    fun plusActivityComponent() : ActivityComponent

    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent
        @BindsInstance
        fun application(application: Application): Builder
    }

}