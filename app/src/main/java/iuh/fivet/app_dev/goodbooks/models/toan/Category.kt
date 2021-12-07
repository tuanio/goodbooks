package iuh.fivet.app_dev.goodbooks.models.toan

import iuh.fivet.app_dev.goodbooks.models.Book

data class Category(
    var nameCategory: String,
    var books : List<BookRated>
)
