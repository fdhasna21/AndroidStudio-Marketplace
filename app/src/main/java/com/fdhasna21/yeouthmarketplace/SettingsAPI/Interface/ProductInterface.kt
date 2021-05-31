package com.fdhasna21.yeouthmarketplace.SettingsAPI.Interface

import com.fdhasna21.yeouthmarketplace.SettingsAPI.ResponseDataClass.SuccessResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductInterface {
    @GET("products")
    fun allProducts(@Query("by") by:String?,
                    @Query("merchandise") merchandiseID:Int?,
                    @Query("group") groupID:Int?,
                    @Query("search") searchKey:String?) : Call<SuccessResponse>
}