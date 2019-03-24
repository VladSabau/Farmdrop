package com.farmdrop.producers.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Vlad Sabau on 23.03.19.
 */
@Entity
data class Pagination(
    @SerializedName("current") @PrimaryKey var current: Int? = 0,
    @SerializedName("previous") var previous: Int? = 0,
    @SerializedName("next") var next: Int? = 0,
    @SerializedName("per_page") var perPage: Int? = 0,
    @SerializedName("pages") var pages: Int? = 0,
    @SerializedName("count") var count: Int? = 0
) {
    constructor() : this(0, 0, 0, 0, 0, 0)
}