package iuh.fivet.app_dev.goodbooks.models.get_book_similar


import com.squareup.moshi.Json
import com.google.gson.annotations.Expose

data class BookSimilar(
    @Json(name = "id")
    val id: Int,
    @Json(name = "image_url")
    val imageUrl: String,
    @Json(name = "rating")
    val rating: Double,
    @Json(name = "title")
    val title: String
)