package iuh.fivet.app_dev.goodbooks.models

import com.squareup.moshi.Json

data class TopBooksByAuthor(
    @Json(name = "id") val id: Int,
    @Json(name = "image_url") val imgUrl: String
//    @Json(name = "title") val title: String,
//    @Json(name = "weighted_rating") val weighted_rating: Float
)
