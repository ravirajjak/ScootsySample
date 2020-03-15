package com.blacksmith.scootsysample.data.model

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)