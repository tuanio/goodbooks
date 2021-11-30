package iuh.fivet.app_dev.goodbooks.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import iuh.fivet.app_dev.goodbooks.models.Response
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
    suspend fun getTop100Books(): Response

}

object Api {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}