package iuh.fivet.app_dev.goodbooks.models.homeData

import com.squareup.moshi.Json

data class BookHome(
    val id: Int,
    @Json(name="image_url")
    val imageUrl: String,
    val rating : Double,
    val title: String
)
