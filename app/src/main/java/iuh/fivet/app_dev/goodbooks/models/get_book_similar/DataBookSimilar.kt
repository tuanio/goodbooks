package iuh.fivet.app_dev.goodbooks.models.get_book_similar


import com.squareup.moshi.Json
import com.google.gson.annotations.Expose

data class DataBookSimilar(
    @Json(name = "data")
    val `data`: Data,
    @Json(name = "msg")
    val msg: String,
    @Json(name = "status_code")
    val statusCode: Int
)