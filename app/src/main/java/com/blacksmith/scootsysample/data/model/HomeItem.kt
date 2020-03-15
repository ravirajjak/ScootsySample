package com.blacksmith.scootsysample.data.model

import com.blacksmith.scootsysample.repository.model.HomeData
import com.google.gson.annotations.SerializedName

data class HomeItem(@SerializedName("data") val data: List<HomeData>)
