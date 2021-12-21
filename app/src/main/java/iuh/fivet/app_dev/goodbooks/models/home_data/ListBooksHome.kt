package iuh.fivet.app_dev.goodbooks.models.home_data

import com.squareup.moshi.Json
import iuh.fivet.app_dev.goodbooks.models.list_books.Book

data class ListBooksHome(
    @Json(name = "list_book")
    val listBooks: List<Book>
)
