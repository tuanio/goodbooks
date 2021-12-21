package iuh.fivet.app_dev.goodbooks.models.get_user_rating


import com.squareup.moshi.Json
import com.google.gson.annotations.Expose

data class RatingData(
    @Json(name = "rating")
    @Expose
    val rating: Int
)