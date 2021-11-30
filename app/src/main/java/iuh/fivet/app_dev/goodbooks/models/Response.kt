package iuh.fivet.app_dev.goodbooks.models

import com.squareup.moshi.Json

data class Response(
    @Json(name = "data") val data: List_book_top100Book,
    @Json(name = "msg") val msg: String,
    @Json(name = "status_code") val status_code: Int
)
