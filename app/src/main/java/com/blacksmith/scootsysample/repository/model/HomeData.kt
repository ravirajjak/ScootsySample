package com.blacksmith.scootsysample.repository.model

import com.blacksmith.scootsysample.data.model.Category
import com.blacksmith.scootsysample.data.model.VendorTypeDetails
import com.google.gson.annotations.SerializedName

data class HomeData(
    @SerializedName("category") val category: List<Category>,
    @SerializedName("vendor_type_details") val vendor_type_details: List<VendorTypeDetails>,
    @SerializedName("image") val image: String
)