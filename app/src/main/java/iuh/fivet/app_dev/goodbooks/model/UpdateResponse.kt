package iuh.fivet.app_dev.goodbooks.model


import com.google.gson.annotations.SerializedName

data class UpdateResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("status_code")
    val statusCode: Int
)