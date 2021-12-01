package iuh.fivet.app_dev.goodbooks.repository

import iuh.fivet.app_dev.goodbooks.api.RetrofitInstance
import iuh.fivet.app_dev.goodbooks.model.Books
import iuh.fivet.app_dev.goodbooks.model.ListBookRating

class Repository {

    suspend fun getBooks():ListBookRating{
        return RetrofitInstance.api.getBooks()
    }


}