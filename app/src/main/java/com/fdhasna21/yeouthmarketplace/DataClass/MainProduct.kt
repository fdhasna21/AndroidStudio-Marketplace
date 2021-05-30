package com.fdhasna21.yeouthmarketplace.DataClass

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class MainProduct(
    @SerializedName("id") val productID : String?,
    @SerializedName("product_name") val productName : String?,
    @SerializedName("product_category") val productCategory: String?,
    @SerializedName("product_detail") val productDetail : String?,
    @SerializedName("product_release") val productRelease : Date?,
    @SerializedName("product_sold") val productSold : Int?,
    @SerializedName("product_rate") val productRate : Double?,
    @SerializedName("product_wishlisted") val productWishlisted : Int?,
    @SerializedName("category_merchandise_id") val productMerchID : Int?,
    @SerializedName("category_group_id") val productGroupID : Int?,
    @SerializedName("version_products") val productVersion : ArrayList<VersionProduct>?,
    @SerializedName("images") val versionImage : ArrayList<String>?
)
