package iuh.fivet.app_dev.goodbooks.models.book_rated_favorited

import com.google.gson.annotations.SerializedName

data class ListBooksRated(
    @SerializedName("list_book")
    val listBooks: List<BookRated>
)
