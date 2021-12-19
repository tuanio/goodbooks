package iuh.fivet.app_dev.goodbooks.models

import com.squareup.moshi.Json

data class Top1Book(
    @Json(name = "author")
    val authors: String,
    val desc: String,
    val genres: String,
    val id: Int,
    val image_url: String,
    val rating: Float,
    val reviews: Int,
    val title: String,
    val total_ratings: Int
)
