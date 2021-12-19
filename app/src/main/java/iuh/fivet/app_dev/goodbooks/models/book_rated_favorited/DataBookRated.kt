package iuh.fivet.app_dev.goodbooks.models.book_rated_favorited

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class DataBookRated(
    @Json(name="status_code")
    val statusCode: Int,
    val msg: String,
    val data: ListBooksRated
)
