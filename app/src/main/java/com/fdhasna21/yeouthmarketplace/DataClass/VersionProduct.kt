package com.fdhasna21.yeouthmarketplace.DataClass

import com.google.gson.annotations.SerializedName

data class VersionProduct(
    @SerializedName("id") val versionID : Int?,
    @SerializedName("version_name") val versionName : String?,
    @SerializedName("version_detail") val versionDetail : String?,
    @SerializedName("version_price_created") val versionPriceCreated : Int?,
    @SerializedName("version_price") val versionPrice : Int?,
    @SerializedName("version_stock") val versionStock : Int?,
    @SerializedName("version_sold") val versionSold : Int?,
    @SerializedName("main_product_id") val versionProductID : Int?,
    @SerializedName("images") val versionImage : ArrayList<String>?
)
