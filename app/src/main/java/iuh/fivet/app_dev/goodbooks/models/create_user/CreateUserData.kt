package iuh.fivet.app_dev.goodbooks.models.create_user

import com.squareup.moshi.Json

data class CreateUserData(
    val data: UserIdData,
    val msg: String,
    @Json(name="status_code")
    val statusCode: Int
)
