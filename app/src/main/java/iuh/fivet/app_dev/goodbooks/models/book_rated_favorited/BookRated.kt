package iuh.fivet.app_dev.goodbooks.models.book_rated_favorited

import com.google.gson.annotations.SerializedName

data class BookRated(
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    val rating : Double,
    val title: String
)
