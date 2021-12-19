package iuh.fivet.app_dev.goodbooks.models.list_genres

import com.squareup.moshi.Json

data class DataGenres(
    @Json(name="status_code")
    val statusCode: Int,
    val msg: String,
    val data: ListGenres
)
