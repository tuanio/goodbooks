package iuh.fivet.app_dev.goodbooks.models.get_user

import com.squareup.moshi.Json

data class DetailUserData(
    val id: Int,
    val uid: String,
    @Json(name="user_email")
    val userEmail: String,
    @Json(name="user_name")
    val userName: String
)
