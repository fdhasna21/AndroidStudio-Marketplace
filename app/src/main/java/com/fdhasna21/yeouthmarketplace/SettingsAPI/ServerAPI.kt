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

    fun setCircularProgress(context: Context) : CircularProgressIndicator{
        return CircularProgressIndicator(context)
    }

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