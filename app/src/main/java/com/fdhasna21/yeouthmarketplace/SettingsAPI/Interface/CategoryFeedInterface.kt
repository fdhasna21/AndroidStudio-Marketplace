package com.fdhasna21.yeouthmarketplace.SettingsAPI.Interface

import com.fdhasna21.yeouthmarketplace.DataClass.Category
import com.fdhasna21.yeouthmarketplace.DataClass.Feed
import com.fdhasna21.yeouthmarketplace.SettingsAPI.ResponseDataClass.SuccessResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryFeedInterface {
    @GET("category") //by ("group"/"merchandise") || limit (numeric)
    fun categoryProduct(@Query("by") by:String?,
                        @Query("limit") limit:Int?) : Call<Category>

    @GET("feeds")
    fun feedProduct(@Query("limit") limit:Int?) : Call <Feed>

//    @GET("feed/bestSeller") //limit (numeric)
//    fun feedBestSeller(@Query("limit") limit:Int?) : Call<SuccessResponse>
//
//    @GET("feed/newCollection") //limit (numeric)
//    fun feedNewCollection(@Query("limit") limit:Int?) : Call<SuccessResponse>
//
//    @GET("feed/trendingMerchandise") //limit (numeric)
//    fun feedTrendingMerchandise(@Query("limit") limit:Int?) : Call<SuccessResponse>
}