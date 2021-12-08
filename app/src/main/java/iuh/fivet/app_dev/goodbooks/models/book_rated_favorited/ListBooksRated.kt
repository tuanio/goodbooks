package iuh.fivet.app_dev.goodbooks.models.book_rated_favorited

import com.google.gson.annotations.SerializedName

data class ListBooksRated(
    @SerializedName("list_books")
    val listBooks: List<BookRated>
)
