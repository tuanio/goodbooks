package iuh.fivet.app_dev.goodbooks.models.book_rated_favorited

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class ListBooksRated(
    @Json(name="list_book")
    val listBooks: List<BookRated>
)
