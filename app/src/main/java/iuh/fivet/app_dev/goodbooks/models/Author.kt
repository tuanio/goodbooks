package iuh.fivet.app_dev.goodbooks.models

import com.google.gson.annotations.SerializedName

data class Author(
    @SerializedName("full_name")
    val fullName: String,
    val id: Int
)
