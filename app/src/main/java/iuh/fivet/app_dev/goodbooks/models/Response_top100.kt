package iuh.fivet.app_dev.goodbooks.models

import com.squareup.moshi.Json

data class Response_top100(
    @Json(name = "data") val data: List_book_top100,
    @Json(name = "msg") val msg: String,
    @Json(name = "status_code") val status_code: Int
)
