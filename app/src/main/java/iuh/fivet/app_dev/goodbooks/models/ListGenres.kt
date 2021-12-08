package iuh.fivet.app_dev.goodbooks.models

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class ListGenres(
    @Json(name="list_genres")
    val listGenres: List<Genre>
)
