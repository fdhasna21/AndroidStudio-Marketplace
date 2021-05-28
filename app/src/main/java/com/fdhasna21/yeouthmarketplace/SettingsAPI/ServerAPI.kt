package com.fdhasna21.yeouthmarketplace.SettingsAPI

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ServerAPI {
    var BASE_URL : String = "http://fdhasna.teknisitik.com/api/v1/"
    var retrofit : Retrofit? = null


    fun getServerAPI() : Retrofit?{
        if(retrofit == null){
            val gson = GsonBuilder()
                .setLenient()
                .create()

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit
    }
}