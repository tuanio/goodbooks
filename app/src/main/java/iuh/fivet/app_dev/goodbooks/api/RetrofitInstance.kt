package iuh.fivet.app_dev.goodbooks.api

import iuh.fivet.app_dev.goodbooks.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api : SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }
}