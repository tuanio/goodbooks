package iuh.fivet.app_dev.goodbooks.models

import com.squareup.moshi.Json

data class List_book_top100(
    @Json(name = "list_book") val list_book_top100: List<Top100Books>
)
