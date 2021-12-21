package iuh.fivet.app_dev.goodbooks.models.homeData

import com.squareup.moshi.Json

data class DataTop1Book (
    @Json(name = "status_code")
    val statusCode: Int,
    val msg: String,
    val data: Top1Book
)