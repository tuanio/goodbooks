package iuh.fivet.app_dev.goodbooks.api

import iuh.fivet.app_dev.goodbooks.model.UpdateResponse
import retrofit2.Call
import retrofit2.http.PUT
import retrofit2.http.Path

interface APIServiceUpdate {
    @PUT ("/api/update-user-rating/{user_id}/{book_id}/{user_rating}")
    fun updateUserRating(@Path("user_id") user_id: Int,
                         @Path("book_id") book_id: Int,
                         @Path("user_rating") user_rating: Int): Call<UpdateResponse>

    @PUT ("/api/update-favorite/{user_id}/{book_id}")
    fun updateUserFavourite(@Path("user_id") user_id: Int,
                            @Path("book_id") book_id: Int): Call<UpdateResponse>

    @PUT ("/api/update-count-author-genre/{user_id}/{book_id}")
    fun updateCountAuthorGenre(@Path("user_id") user_id: Int,
                               @Path("book_id") book_id: Int): Call<UpdateResponse>
}