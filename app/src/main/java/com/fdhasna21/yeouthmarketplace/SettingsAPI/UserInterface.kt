package com.fdhasna21.yeouthmarketplace.SettingsAPI

import com.fdhasna21.yeouthmarketplace.DataClass.UserInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserInterface{
    @Headers("Accept: application/json")
    @POST("user/login")
    fun userLogin(@Body userAuth : UserInfo) : Call<UserInfo>
}