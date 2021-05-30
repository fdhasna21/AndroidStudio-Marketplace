package com.fdhasna21.yeouthmarketplace.SettingsAPI.ResponseDataClass

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ErrorResponse (
    @SerializedName("message")
    val message : String?,
    @SerializedName("errors")
    val errors : Errors?
){
    @Parcelize
    data class Errors(
//For User
            @SerializedName("name") val name : List<String>?,
            @SerializedName("email") val email : List<String>?,
            @SerializedName("password") val password : List<String>?,
            @SerializedName("password_confirmation") val password_confirmation : List<String>?
    ) : Parcelable
}