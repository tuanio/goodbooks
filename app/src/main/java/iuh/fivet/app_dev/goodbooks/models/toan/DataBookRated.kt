package iuh.fivet.app_dev.goodbooks.models.toan

import com.google.gson.annotations.SerializedName
import iuh.fivet.app_dev.goodbooks.models.ListGenres

data class DataBookRated(
    @SerializedName("status_code")
    val statusCode: Int,
    val msg: String,
    val data: ListBooksRated
)
