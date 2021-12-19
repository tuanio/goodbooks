package iuh.fivet.app_dev.goodbooks.models.list_authors

import com.squareup.moshi.Json

data class DataAuthors(
    @Json(name="status_code")
    val statusCode: Int,
    val msg: String,
    val data: ListAuthors
)
