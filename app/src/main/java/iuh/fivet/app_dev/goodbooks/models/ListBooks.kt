package iuh.fivet.app_dev.goodbooks.models

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class ListBooks(
    @Json(name="list_book")
    val listBook: List<Book>,
    @Json(name="no_books")
    val noBooks: Int
)
