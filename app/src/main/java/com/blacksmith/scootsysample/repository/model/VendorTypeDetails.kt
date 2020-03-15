package com.blacksmith.scootsysample.data.model

import com.google.gson.annotations.SerializedName

data class VendorTypeDetails(
    @SerializedName("app_image") val app_image: String,
    @SerializedName("spotlight_app_image") val spotlight_app_image: String,
    @SerializedName("image") val image: String,
    @SerializedName("name") val name: String

)