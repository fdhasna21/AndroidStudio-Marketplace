package com.fdhasna21.yeouthmarketplace.SettingsAPI

import android.content.Context
import com.fdhasna21.yeouthmarketplace.apiToken
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ServerAPI {
    var BASE_URL : String = "http://fdhasna.teknisitik.com/api/v1/"
    var retrofit : Retrofit? = null
    var httpClient = OkHttpClient.Builder()

    //TODO : fixing endpoint and its response (laravel) to get exact data that needed, trim the unused one! (try to learn how to make view MySQL in laravel)
    //done : user(login, register)
    //done but should be repaired : category_feeds(all for home/feeds, show its products), product(all for category, each by id)
    //not done yet : user(edit user_detail), wishlist(add/remove, check certain product, show all), shoppingbag(add/edit, delete, show all), order(add all, add based on selection)

    fun getServerAPI() : Retrofit?{
        if(retrofit == null){
            httpClient.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val original : Request = chain.request()

                    val request = original.newBuilder()
                        .header("Accept", "application/json")
                        .header("Authorization", "Bearer $apiToken")
                        .method(original.method, original.body)
                        .build()

                    return chain.proceed(request)
                }
            })

            val gson = GsonBuilder()
                .setLenient()
                .create()

            val client = httpClient.build()

            retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit
    }
}