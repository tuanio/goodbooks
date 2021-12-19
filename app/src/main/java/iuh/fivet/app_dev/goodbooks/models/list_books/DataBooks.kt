package iuh.fivet.app_dev.goodbooks.models.list_books

import com.squareup.moshi.Json

data class DataBooks(
    @Json(name="status_code")
    val statusCode: Int,
    val msg: String,
    val data: ListBooks
)
