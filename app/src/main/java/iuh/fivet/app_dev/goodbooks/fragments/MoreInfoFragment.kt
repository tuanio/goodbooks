package iuh.fivet.app_dev.goodbooks.fragments

import android.graphics.Color

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import iuh.fivet.app_dev.goodbooks.R
import iuh.fivet.app_dev.goodbooks.api.Api
import iuh.fivet.app_dev.goodbooks.models.get_book.DataBook
import iuh.fivet.app_dev.goodbooks.models.get_book.DataGetBook
import iuh.fivet.app_dev.goodbooks.utils.GlobalVariables
import kotlinx.android.synthetic.main.fragment_more_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoreInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val bookId = GlobalVariables.bookId
        val userId = GlobalVariables.userId

        val requestGetBookDetail = Api.retrofitService.getBookDetail(userId, bookId)
        requestGetBookDetail.enqueue(object : Callback<DataGetBook> {
            override fun onResponse(call: Call<DataGetBook>, response: Response<DataGetBook>) {

                val book: DataBook = response.body()!!.data
                val authors: ArrayList<String> = book.authors as ArrayList<String>

                val authorsString = authors.joinToString(", ")
                val title = book.title
                val rating = book.rating.toFloat()
                val totalRatings = book.totalRatings.toString()
                val bookUrl = book.bookUrl
                val isbn = book.isbn
                val pages = book.pages
                val reviews = book.reviews
                val genres = book.genres.joinToString(", ")

                val bookInfo = """
                    Title: $title
                    
                    Authors: $authorsString

                    Book's link: $bookUrl

                    Genres: $genres

                    isbn: $isbn

                    Pages: $pages

                    Rating: $rating

                    Reviews: $reviews

                    Total_ratings: $totalRatings
                    """.trimIndent()

                book_more_info_text.movementMethod = ScrollingMovementMethod()
                book_more_info_text.text = bookInfo
                book_more_info_text.setTrimExpandedText(" show less")
                book_more_info_text.setTrimCollapsedText(" show more")
                book_more_info_text.setTrimLength(20)
                book_more_info_text.setTrimLines(5)
                book_more_info_text.setColorClickableText(Color.BLUE)

            }

            override fun onFailure(call: Call<DataGetBook>, t: Throwable) {
                Log.d("MoreInfoTabError", "$t")
            }
        })

        return inflater.inflate(R.layout.fragment_more_info, container, false)
    }

}