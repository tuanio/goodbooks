package iuh.fivet.app_dev.goodbooks.models.toan

import com.google.gson.annotations.SerializedName

data class BookRated(
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    val rating : Double,
    val title: String
)
