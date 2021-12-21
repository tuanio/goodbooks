package iuh.fivet.app_dev.goodbooks.models.get_user_rating


import com.squareup.moshi.Json
import com.google.gson.annotations.Expose

data class UserRatingBook(
    @Json(name = "data")
    val `data`: RatingData,
    @Json(name = "msg")
    val msg: String,
    @Json(name = "status_code")
    val statusCode: Int
)