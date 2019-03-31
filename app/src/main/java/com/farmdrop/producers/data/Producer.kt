package com.farmdrop.producers.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Vlad Sabau on 23.03.19.
 */
@Entity
data class Producer(
    @SerializedName("id") @PrimaryKey var id: Int = 0,
    @SerializedName("name") var name: String = "",
    @SerializedName("permalink") var permalink: String = "",
    @SerializedName("created_at") var created_at: String = "",
    @SerializedName("updated_at") var updated_at: String = "",
    @SerializedName("images") var images: List<Image> = ArrayList(),
    @SerializedName("short_description") var short_description: String = "",
    @SerializedName("description") var description: String = "",
    @SerializedName("location") var location: String = "",
    @SerializedName("via_wholesaler") var via_wholesaler: Boolean = false,
    @SerializedName("wholesaler_name") var wholesaler_name: String = ""
)