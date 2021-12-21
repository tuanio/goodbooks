package iuh.fivet.app_dev.goodbooks.models.home_data

import com.squareup.moshi.Json

data class Top1Book(
    @Json(name = "authors")
    val authors: List<String>,
    @Json(name = "book_url")
    val book_url: String,
    @Json(name = "desc")
    val desc: String,
    @Json(name = "genres")
    val genres: List<String>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "image_url")
    val image_url: String,
    @Json(name = "rating")
    val rating: Float,
    @Json(name = "reviews")
    val reviews: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "total_ratings")
    val total_ratings: Int
)
