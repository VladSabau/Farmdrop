package com.farmdrop.producers.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import com.farmdrop.producers.data.database.AppDatabase
import com.farmdrop.producers.ui.producer.ProducersViewModel

/**
 * Created by Vlad Sabau on 23.03.19.
 */
class ViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProducersViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "db-producers").build()
            @Suppress("UNCHECKED_CAST")
            return ProducersViewModel(db.producersDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}