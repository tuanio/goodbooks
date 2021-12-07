package iuh.fivet.app_dev.goodbooks.models

import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("image_url")
    val imageUrl: String,
    val title: String,
    val id: Int,
    val rating: Double
)
