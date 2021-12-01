package iuh.fivet.app_dev.goodbooks.models

import com.google.gson.annotations.SerializedName

data class DataGenres(
    @SerializedName("status_code")
    val statusCode: Int,
    val msg: String,
    val data: ListGenres
)
