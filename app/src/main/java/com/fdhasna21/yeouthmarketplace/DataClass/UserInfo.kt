package com.fdhasna21.yeouthmarketplace.DataClass

import android.os.Parcelable
import com.fdhasna21.yeouthmarketplace.emptyString
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class UserInfo(
    @SerializedName("name") val userName: String?,
    @SerializedName("email") val userEmail: String?,
    @SerializedName("password") val userPassword: String?,
    @SerializedName("password_confirmation") val userPasswordConfirmation: String?,
    @SerializedName("api_token") val userAPItoken: String?
){
    constructor(userEmail: String?, userPassword: String?) :
        this(emptyString, userEmail, userPassword, emptyString, emptyString)

    constructor(userName: String?, userEmail: String?, userPassword: String?, userPasswordConfirmation: String?) :
        this(userName, userEmail, userPassword, userPasswordConfirmation, emptyString)
}