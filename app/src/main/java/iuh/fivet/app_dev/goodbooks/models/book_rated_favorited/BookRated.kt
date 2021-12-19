package iuh.fivet.app_dev.goodbooks.models.book_rated_favorited

import com.squareup.moshi.Json

data class BookRated(
    val id: Int,
    @Json(name="image_url")
    val imageUrl: String,
    val rating : Double,
    val title: String
)
