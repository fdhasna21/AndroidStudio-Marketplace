package com.fdhasna21.yeouthmarketplace.DataClass

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

val emptyString = ' '.toString()
val emptyUserAuth = UserInfo.UserAuth(listOf(emptyString), listOf(emptyString))
data class UserInfo(
    @SerializedName("name") val userName: String?,
    @SerializedName("email") val userEmail: String?,
    @SerializedName("password") val userPassword: String?,
    @SerializedName("password_confirmation") val userPasswordConfirmation: String?,
    @SerializedName("api_token") val userAPItoken: String?,

    @SerializedName("message") val userMessage: String?,
    @SerializedName("errors") val userErrors: UserAuth?
){
    constructor(userEmail: String?, userPassword: String?) :
            this(
                emptyString,
                userEmail,
                userPassword,
                emptyString,
                emptyString,
                emptyString,
                emptyUserAuth
            )

    @Parcelize
    data class UserAuth(
        @SerializedName("email") val errorEmail: List<String>,
        @SerializedName("password") val errorPassword: List<String>
    ) : Parcelable
}