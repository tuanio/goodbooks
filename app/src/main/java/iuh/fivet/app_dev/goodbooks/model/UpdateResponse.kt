package iuh.fivet.app_dev.goodbooks.model


import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class UpdateResponse(
   @Json(name="data")
    val `data`: Data,
    @Json(name="msg")
    val msg: String,
    @Json(name="status_code")
    val statusCode: Int
)