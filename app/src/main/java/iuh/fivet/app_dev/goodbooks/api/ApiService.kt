package iuh.fivet.app_dev.goodbooks.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import iuh.fivet.app_dev.goodbooks.models.Response_top100
import iuh.fivet.app_dev.goodbooks.models.Response_topAuthor
import iuh.fivet.app_dev.goodbooks.models.Response_topGenre
import iuh.fivet.app_dev.goodbooks.models.Response_topSimilar
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://backend-recommender-system-book.up.railway.app/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {

    @GET("get-top-100-books")
    suspend fun getTop100Books(): Response_top100

    @GET("/api/get-list-book-recommend-by-author/1")
    suspend fun getTopBooksByAuthor(): Response_topAuthor

    @GET("/api/get-list-book-recommend-by-genre/1")
    suspend fun getTopBooksByGenre(): Response_topGenre

    @GET("/api/get-some-book-similar-at-all/1")
    suspend fun getTopBooksSimilar(): Response_topSimilar

}

object Api {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}