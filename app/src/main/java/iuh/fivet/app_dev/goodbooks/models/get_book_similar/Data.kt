package iuh.fivet.app_dev.goodbooks.models.get_book_similar


import com.squareup.moshi.Json

data class Data(
    @Json(name = "list_book")
    val listBook: List<BookSimilar>
)