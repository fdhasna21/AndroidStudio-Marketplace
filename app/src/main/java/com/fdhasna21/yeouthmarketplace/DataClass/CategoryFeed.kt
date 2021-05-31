package com.fdhasna21.yeouthmarketplace.DataClass

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("merchandises") val merch : ArrayList<CategoryMerchandise>?,
    @SerializedName("groups") val group : ArrayList<CategoryGroup>?
    )

data class CategoryMerchandise (
    @SerializedName("id") val merchID : Int?,
    @SerializedName("merchandise_name") val merchName : String?,
    @SerializedName("mainProducts") val merchProduct : ArrayList<MainProduct>?,
    @SerializedName("image") val merchImage : String?
    )

data class CategoryGroup(
    @SerializedName("id") val groupID : Int?,
    @SerializedName("group_name") val groupName : String?,
    @SerializedName("group_detail") val groupDetail : String?,
    @SerializedName("mainProducts") val groupProduct : ArrayList<MainProduct>?,
    @SerializedName("image") val groupImage : String?
    )

data class Feed(
    @SerializedName("newCollection") val newCollection : ArrayList<MainProduct>?,
    @SerializedName("trendingMerchandise") val trendingMerchandise : ArrayList<MainProduct>?,
    @SerializedName("bestSeller") val bestSeller : ArrayList<MainProduct>?
)