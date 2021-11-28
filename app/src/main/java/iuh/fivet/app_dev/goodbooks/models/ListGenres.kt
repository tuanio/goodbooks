package iuh.fivet.app_dev.goodbooks.models

import com.google.gson.annotations.SerializedName

data class ListGenres(
    @SerializedName("list_genres")
    val listGenres: List<Genre>
)
