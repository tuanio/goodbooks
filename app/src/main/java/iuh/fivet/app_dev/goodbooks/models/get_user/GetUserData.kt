package iuh.fivet.app_dev.goodbooks.models.get_user

import com.squareup.moshi.Json

data class GetUserData(
    val data: GetUserUserData,
    val msg: String,
    @Json(name="status_code")
    val statusCode: Int
)
