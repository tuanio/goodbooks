package iuh.fivet.app_dev.goodbooks.models.toan

import com.google.gson.annotations.SerializedName
import iuh.fivet.app_dev.goodbooks.models.Book

data class ListBooksRated(
    @SerializedName("list_books")
    val listBooks: List<BookRated>
)
