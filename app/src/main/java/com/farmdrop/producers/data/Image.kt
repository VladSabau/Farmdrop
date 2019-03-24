package com.farmdrop.producers.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Vlad Sabau on 23.03.19.
 */
@Entity
data class Image(
    @SerializedName("path") val path: String = "",
    @SerializedName("position") @PrimaryKey val position: Int = 0
) {
    constructor() : this("", 0)
}