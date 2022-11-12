package com.iruda.ecomm.data.global

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class ApiFactory {

    open val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    companion object {

        private const val BASE_URL = "https://dummyjson.com/"
    }
}