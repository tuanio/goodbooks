package iuh.fivet.app_dev.goodbooks.models

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class DataBooksHome(
    @Json(name = "status_code")
    val statusCode: Int,
    val msg: String,
    val data: ListBooksHome
)
