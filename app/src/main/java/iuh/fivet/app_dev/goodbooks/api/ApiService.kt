package iuh.fivet.app_dev.goodbooks.api

import iuh.fivet.app_dev.goodbooks.models.DataAuthors
import iuh.fivet.app_dev.goodbooks.models.DataGenres
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/get-all-authors")
    fun getListAuthors(): Call<DataAuthors>

    @GET("api/get-all-genres")
    fun getListGenres(): Call<DataGenres>
}