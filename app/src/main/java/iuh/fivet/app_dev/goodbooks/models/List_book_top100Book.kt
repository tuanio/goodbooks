package iuh.fivet.app_dev.goodbooks.models

import com.squareup.moshi.Json

data class List_book_top100Book(
    @Json(name = "list_book") val list_book: List<Top100Books>
)
