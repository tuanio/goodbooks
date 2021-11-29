package iuh.fivet.app_dev.goodbooks.api

import iuh.fivet.app_dev.goodbooks.models.DataAuthors
import iuh.fivet.app_dev.goodbooks.models.DataBooks
import iuh.fivet.app_dev.goodbooks.models.DataGenres
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("api/get-all-authors")
    fun getListAuthors(): Call<DataAuthors>

    @GET("api/get-all-genres")
    fun getListGenres(): Call<DataGenres>

    @GET("api/get-list-books-by-author-genre/{author_id}/{genre_id}")
    fun getBooksByAuthorAndGenre(
        @Path("author_id") authorId: Int,
        @Path("genre_id") genreId: Int
    ): Call<DataBooks>
}