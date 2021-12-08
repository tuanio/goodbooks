package iuh.fivet.app_dev.goodbooks.models

import com.google.gson.annotations.SerializedName

data class ListBooks(
    @SerializedName("list_book")
    val listBook: List<Book>,
    @SerializedName("no_books")
    val noBooks: Int
)
