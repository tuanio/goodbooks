package iuh.fivet.app_dev.goodbooks.models.list_genres

import com.squareup.moshi.Json

data class ListGenres(
    @Json(name="list_genres")
    val listGenres: List<Genre>
)
