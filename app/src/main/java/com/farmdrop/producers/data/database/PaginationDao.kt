package com.farmdrop.producers.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.farmdrop.producers.data.Pagination

/**
 * Created by Vlad Sabau on 24.03.19.
 */
@Dao
interface PaginationDao {
    @get:Query("SELECT * FROM pagination")
    val pagination: Pagination

    //TODO: check if you need to insert all
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPagination(pagination: Pagination)
}