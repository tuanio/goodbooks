package iuh.fivet.app_dev.goodbooks.models

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class ListAuthors(
    @Json(name="list_authors")
    val listAuthors: List<Author>
)
