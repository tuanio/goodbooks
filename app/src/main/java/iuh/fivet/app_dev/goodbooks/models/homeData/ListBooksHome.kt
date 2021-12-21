package iuh.fivet.app_dev.goodbooks.models.homeData

import com.squareup.moshi.Json


data class ListBooksHome(
    @Json(name = "list_book")
    val listBooks: List<BookHome>
)
