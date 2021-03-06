package iuh.fivet.app_dev.goodbooks.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

import iuh.fivet.app_dev.goodbooks.models.home_data.DataBooksHome
import iuh.fivet.app_dev.goodbooks.models.home_data.DataTop1Book

import iuh.fivet.app_dev.goodbooks.models.list_authors.DataAuthors
import iuh.fivet.app_dev.goodbooks.models.list_books.DataBooks
import iuh.fivet.app_dev.goodbooks.models.list_genres.DataGenres
import iuh.fivet.app_dev.goodbooks.models.book_rated_favorited.DataBookRated
import iuh.fivet.app_dev.goodbooks.models.create_user.CreateUserData
import iuh.fivet.app_dev.goodbooks.models.create_user.PostUserData
import iuh.fivet.app_dev.goodbooks.models.get_book.DataGetBook
import iuh.fivet.app_dev.goodbooks.models.get_book_similar.DataUserRating
import iuh.fivet.app_dev.goodbooks.models.get_user.GetUserData
import iuh.fivet.app_dev.goodbooks.models.get_user_rating.UserRatingBook
import iuh.fivet.app_dev.goodbooks.utils.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Constants.BASE_URL)
    .build()

interface ApiService {
    @GET("/api/get-all-authors")
    fun getListAuthors(): Call<DataAuthors>

    @GET("/api/get-all-genres")
    fun getListGenres(): Call<DataGenres>

    @GET("/api/get-list-books-by-author-genre/{author_id}/{genre_id}")
    fun getBooksByAuthorAndGenre(
        @Path("author_id") authorId: Int,
        @Path("genre_id") genreId: Int
    ): Call<DataBooks>
    @GET("/api/get-list-book-rated/{user_id}")
    fun getBookListBookRated(
        @Path("user_id") userId : Int,
    ): Call<DataBookRated>
    @GET("/api/get-book-favorited/{user_id}")
    fun getBookListBookFavorited(
        @Path("user_id") userId : Int,
    ): Call<DataBookRated>
    @GET("/api/get-reading-list-history/{user_id}")
    fun getBookListBookHistory(
        @Path("user_id") userId : Int,
    ): Call<DataBookRated>

    @Headers("Content-Type: application/json")
    @POST("/api/create-user")
    fun createUser(@Body userData: PostUserData): Call<CreateUserData> 

    @GET("/api/get-user/{uid}")
    fun getUser(@Path("uid") uid: String): Call<GetUserData>

    @GET("/api/get-one-top-book")
    fun getTheBestBooks(): Call<DataTop1Book>

    @GET("/api/get-top-100-books")
    fun getTop100Books(): Call<DataBooksHome>

    @GET("/api/get-list-book-recommend-by-author/{user_id}")
    fun getTopBooksByAuthor(
        @Path("user_id") userId: Int
    ): Call<DataBooksHome>

    @GET("/api/get-list-book-recommend-by-genre/{user_id}")
    fun getTopBooksByGenre(
        @Path("user_id") userId: Int
    ): Call<DataBooksHome>

    @GET("/api/get-some-book-similar-at-all/{user_id}")
    fun getTopBooksSimilar(
        @Path("user_id") userId: Int
    ): Call<DataBooksHome>

    @GET("/api/get-book-similar/{book_id}")
    fun getBookSimilar(@Path("book_id") book_id: Int): Call<DataUserRating>

    @GET("/api/get-book/{user_id}/{book_id}")
    fun getBookDetail(
        @Path("user_id") user_id: Int,
        @Path("book_id") book_id: Int
    ): Call<DataGetBook>

    @GET("/api/get-user-rating/{user_id}/{book_id}")
    fun getUserRating(
        @Path("user_id") user_id: Int,
        @Path("book_id") book_id: Int
    ): Call<UserRatingBook>

    @GET("/api/get-list-book-rated/{user_id}")
    fun getListBookHearted(@Path("user_id") user_id: Int): Call<DataUserRating>
}

object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
