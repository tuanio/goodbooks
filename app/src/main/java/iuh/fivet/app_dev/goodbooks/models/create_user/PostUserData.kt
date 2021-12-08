package iuh.fivet.app_dev.goodbooks.models.create_user

import com.squareup.moshi.Json

data class PostUserData(
    val uid: String,
    @Json(name="user_name")
    val username: String,
    @Json(name="user_email")
    val userEmail: String
)
