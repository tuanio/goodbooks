package iuh.fivet.app_dev.goodbooks.models.create_user

import com.squareup.moshi.Json

data class UserIdData(
    @Json(name="user_id")
    val userId: Int
)
