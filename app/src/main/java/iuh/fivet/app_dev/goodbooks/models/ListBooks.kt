package iuh.fivet.app_dev.goodbooks.models

import com.google.gson.annotations.SerializedName

data class ListBooks(
    @SerializedName("list_books")
    val listBooks: List<Book>,
    @SerializedName("no_books")
    val noBooks: Int
)
