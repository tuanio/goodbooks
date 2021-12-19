package iuh.fivet.app_dev.goodbooks.models.get_book


import com.squareup.moshi.Json
import com.google.gson.annotations.Expose

data class DataBook(
    @Json(name = "authors")
    val authors: List<String>,
    @Json(name = "book_url")
    val bookUrl: String,
    @Json(name = "desc")
    val desc: String,
    @Json(name = "genres")
    val genres: List<String>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "image_url")
    val imageUrl: String,
    @Json(name = "isbn")
    val isbn: String,
    @Json(name = "isbn13")
    val isbn13: String,
    @Json(name = "pages")
    val pages: Int,
    @Json(name = "rating")
    val rating: Double,
    @Json(name = "reviews")
    val reviews: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "total_ratings")
    val totalRatings: Int
)