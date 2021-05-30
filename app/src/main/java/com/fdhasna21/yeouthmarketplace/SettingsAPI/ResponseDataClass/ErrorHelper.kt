package com.fdhasna21.yeouthmarketplace.SettingsAPI.ResponseDataClass

import com.google.gson.Gson
import retrofit2.Response

class ErrorHelper{
    fun parseErrorBody(response : Response<*>) : ErrorResponse{
        val gson = Gson()
        return gson.fromJson(response.errorBody()!!.string(), ErrorResponse::class.java)
    }

    fun parseErrorCode(response: Response<*>) : Int{
        return response.code()
    }
}