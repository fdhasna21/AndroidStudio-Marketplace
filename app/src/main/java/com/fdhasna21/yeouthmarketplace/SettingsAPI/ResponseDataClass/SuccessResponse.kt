package com.fdhasna21.yeouthmarketplace.SettingsAPI.ResponseDataClass

import com.fdhasna21.yeouthmarketplace.DataClass.MainProduct
import com.google.gson.annotations.SerializedName

data class SuccessResponse(
    //General Output
    @SerializedName("success") val success : String?,

    @SerializedName("total") val total : Int?,
    @SerializedName("products") val products: ArrayList<MainProduct>?,
    @SerializedName("product") val product : MainProduct?
    )
