package iuh.fivet.app_dev.goodbooks.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import iuh.fivet.app_dev.goodbooks.models.DataAuthors
import iuh.fivet.app_dev.goodbooks.models.DataBooks
import iuh.fivet.app_dev.goodbooks.models.DataGenres
import iuh.fivet.app_dev.goodbooks.models.create_user.CreateUserData
import iuh.fivet.app_dev.goodbooks.models.create_user.PostUserData
import iuh.fivet.app_dev.goodbooks.models.get_user.GetUserData
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

    @Headers("Content-Type: application/json")
    @POST("/api/create-user")
    fun createUser(@Body userData: PostUserData): Call<CreateUserData>

    @GET("/api/get-user/{uid}")
    fun getUser(@Path("uid") uid: String): Call<GetUserData>
}

object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
