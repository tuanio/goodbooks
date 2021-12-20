package iuh.fivet.app_dev.goodbooks.models

import com.squareup.moshi.Json

data class Top1Book(
    val authors: List<String>,
    val desc: String,
    @Json(name="book_url")
    val bookUrl: String,
    val genres: List<String>,
    val id: Int,
    val image_url: String,
    val isbn : String,
    val isbn13 : String,
    val pages : Int,
    val rating: Float,
    val reviews: Int,
    val title: String,
    @Json(name="total_ratings")
    val totalRatings: Int
)
