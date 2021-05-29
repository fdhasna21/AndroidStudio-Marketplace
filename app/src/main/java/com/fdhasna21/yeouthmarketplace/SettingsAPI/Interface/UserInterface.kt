package com.fdhasna21.yeouthmarketplace.SettingsAPI.Interface

import com.fdhasna21.yeouthmarketplace.DataClass.UserInfo
import com.fdhasna21.yeouthmarketplace.SettingsAPI.ResponseDataClass.SuccessResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserInterface{
    @POST("user/login") //email, password
    fun userLogin(@Body userInfo : UserInfo) : Call<UserInfo>

    @POST("user/regis") //name, email, password, password_confirmation
    fun userRegis(@Body userInfo: UserInfo) : Call<SuccessResponse>
}