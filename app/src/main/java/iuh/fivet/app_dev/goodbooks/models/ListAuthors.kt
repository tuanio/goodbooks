package iuh.fivet.app_dev.goodbooks.models

import com.google.gson.annotations.SerializedName

data class ListAuthors(
    @SerializedName("list_authors")
    val listAuthors: List<Author>
)
