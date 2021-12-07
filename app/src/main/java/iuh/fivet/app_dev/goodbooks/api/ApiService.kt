package iuh.fivet.app_dev.goodbooks.api

import iuh.fivet.app_dev.goodbooks.models.DataAuthors
import iuh.fivet.app_dev.goodbooks.models.DataBooks
import iuh.fivet.app_dev.goodbooks.models.DataGenres
import iuh.fivet.app_dev.goodbooks.models.toan.DataBookRated
import iuh.fivet.app_dev.goodbooks.utils.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private val retrofit = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

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
    @GET("api/get-list-book-rated/{user_id}")
    fun getBookListBookRated(
        @Path("user_id") userId : Int,
    ): Call<DataBookRated>
}

object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
