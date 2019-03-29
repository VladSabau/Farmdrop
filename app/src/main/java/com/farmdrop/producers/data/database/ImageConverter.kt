package com.farmdrop.producers.data.database

import android.arch.persistence.room.TypeConverter
import com.farmdrop.producers.data.Image
import com.google.gson.Gson

/**
 * Created by Vlad Sabau on 29.03.19.
 */
class ImageConverter {

    private val strSeparator = ","
    private val gson = Gson()

    @TypeConverter
    fun convertListToString(images: List<Image>): String {

        val imagesArray = arrayOfNulls<Image>(images.size)
        for (i in 0 until images.size) {
            imagesArray[i] = images[i]
        }
        var str = ""
        for (i in imagesArray.indices) {
            val jsonString = gson.toJson(imagesArray[i])
            str += jsonString
            if (i < imagesArray.size - 1) {
                str += strSeparator
            }
        }

        return str
    }

    @TypeConverter
    fun convertStringToList(imagesString: String): List<Image> {
        val imagesArray = imagesString.split(strSeparator.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val images = ArrayList<Image>()

        for (i in 0 until imagesArray.size - 1) {
            images.add(gson.fromJson(imagesArray[i], Image::class.java))
        }

        return images
    }
}