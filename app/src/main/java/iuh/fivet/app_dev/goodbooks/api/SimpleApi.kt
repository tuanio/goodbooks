package iuh.fivet.app_dev.goodbooks.api

import iuh.fivet.app_dev.goodbooks.model.Books
import iuh.fivet.app_dev.goodbooks.model.ListBookRating
import retrofit2.http.GET

interface SimpleApi {
    @GET("api/get-list-book-rated/1")
    suspend fun getBooks() :ListBookRating
}