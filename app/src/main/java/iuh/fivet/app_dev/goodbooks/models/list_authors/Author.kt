package iuh.fivet.app_dev.goodbooks.models.list_authors

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class Author(
    @Json(name="full_name")
    val fullName: String,
    val id: Int
)
