package com.blacksmith.scootsysample.data.model

import com.blacksmith.scootsysample.repository.model.HomeData
import com.google.gson.annotations.SerializedName

data class MainContent(
    @SerializedName("data") val data: List<HomeData>,
    @SerializedName("item") val item: HomeItem
)

