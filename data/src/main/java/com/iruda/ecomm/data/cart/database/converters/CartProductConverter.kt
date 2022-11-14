package com.iruda.ecomm.data.cart.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.iruda.ecomm.data.cart.models.CartProductModel


class CartProductConverter {
    private val gson = Gson()

    @TypeConverter
    fun toJson(segments: List<CartProductModel?>?): String? {
        return gson.toJson(segments)
    }

    @TypeConverter
    fun formJson(json: String?): List<CartProductModel?>? {
        return gson.fromJson<List<CartProductModel?>>(
            json,
            object : TypeToken<List<CartProductModel?>?>() {}.type
        )
    }
}