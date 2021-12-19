package iuh.fivet.app_dev.goodbooks.models.get_book


import com.squareup.moshi.Json

data class DataGetBook(
    @Json(name = "data")
    val `data`: DataBook,
    @Json(name = "msg")
    val msg: String,
    @Json(name = "status_code")
    val statusCode: Int
)