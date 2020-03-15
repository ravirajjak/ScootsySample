package com.blacksmith.scootsysample.data.model

import com.google.gson.annotations.SerializedName

data class Header(
    @SerializedName("response") val response: Response,
    @SerializedName("main_content") val main_content: MainContent
)