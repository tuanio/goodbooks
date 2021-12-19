package iuh.fivet.app_dev.goodbooks.models.list_books

import com.squareup.moshi.Json

data class Book(
    @Json(name="image_url")
    val imageUrl: String,
    val title: String,
    val id: Int,
    val rating: Double
)
