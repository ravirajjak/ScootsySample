package com.blacksmith.scootsysample.data.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("name") val name: String,
    @SerializedName("count") val count: Int,
    @SerializedName("image") val image: String,
    @SerializedName("id") val id: String
)