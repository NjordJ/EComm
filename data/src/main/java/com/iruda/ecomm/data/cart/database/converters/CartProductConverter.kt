package com.iruda.ecomm.data.cart.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.iruda.ecomm.data.cart.models.CartProductModel
import java.lang.reflect.Type


class CartProductConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromCartProductList(countryLang: List<CartProductModel?>?): String? {
        if (countryLang == null) {
            return null
        }
        val type: Type = object : TypeToken<List<CartProductModel?>?>() {}.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun toCartProductList(countryLangString: String?): List<CartProductModel>? {
        if (countryLangString == null) {
            return null
        }
        val type: Type = object : TypeToken<List<CartProductModel?>?>() {}.type
        return gson.fromJson<List<CartProductModel>>(countryLangString, type)
    }
}