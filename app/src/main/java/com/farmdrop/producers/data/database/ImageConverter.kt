package com.farmdrop.producers.data.database

import android.arch.persistence.room.TypeConverter
import com.farmdrop.producers.data.Image
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


/**
 * Created by Vlad Sabau on 29.03.19.
 */
class ImageConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToImageObjectList(data: String?): List<Image> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Image>>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun imageObjectListToString(images: List<Image>): String {
        return gson.toJson(images)
    }
}