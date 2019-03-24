package com.farmdrop.producers.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.farmdrop.producers.data.Pagination
import com.farmdrop.producers.data.Producer

/**
 * Created by Vlad Sabau on 23.03.19.
 */
@Database(entities = [(Producer::class), (Pagination::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun producersDao(): ProducersDao
    abstract fun paginationDao(): PaginationDao
}