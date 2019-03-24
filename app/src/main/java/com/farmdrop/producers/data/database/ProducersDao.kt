package com.farmdrop.producers.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.farmdrop.producers.data.Producer

/**
 * Created by Vlad Sabau on 23.03.19.
 */
@Dao
interface ProducersDao {
    @get:Query("SELECT * FROM producer")
    val all: List<Producer>

    //TODO: check if you need to insert all
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(producers: List<Producer>)
}