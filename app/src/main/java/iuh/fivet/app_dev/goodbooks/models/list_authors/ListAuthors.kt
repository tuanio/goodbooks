package iuh.fivet.app_dev.goodbooks.models.list_authors

import com.squareup.moshi.Json

data class ListAuthors(
    @Json(name="list_authors")
    val listAuthors: List<Author>
)
