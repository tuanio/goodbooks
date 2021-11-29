package iuh.fivet.app_dev.goodbooks.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import iuh.fivet.app_dev.goodbooks.BookDetailsActivity
import iuh.fivet.app_dev.goodbooks.BookModel
import iuh.fivet.app_dev.goodbooks.MySingleton
import iuh.fivet.app_dev.goodbooks.R
import kotlinx.android.synthetic.main.fragment_overview.*
import org.json.JSONObject
import android.text.method.ScrollingMovementMethod




class MoreInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_more_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val urlApiGetBook = BookDetailsActivity().urlApiGetBook

        var authors: String?
        var bookUrl: String?
        var desc: String?
        var genres: String?
        var id: Int
        var imageUrl: String?
        var isbn: String?
        var isbn13: String?
        var pages: Int
        var rating: Float
        var reviews: Int
        var title: String?
        var totalRatings: Int

        val bookDetailRequest = JsonObjectRequest(
            Request.Method.GET, urlApiGetBook, null,
            { response ->
                val message = "Success get book!"
                val book: JSONObject = response.getJSONObject("data")

                authors = book.getString("authors")
                bookUrl = book.getString("book_url")
                desc = book.getString("desc")
                genres = book.getString("genres")
                id = book.getInt("id")
                imageUrl = book.getString("image_url")
                isbn = book.getString("isbn")
                isbn13 = book.getString("isbn13")
                pages = book.getInt("pages")
                rating = book.getDouble("rating").toFloat()
                reviews = book.getInt("reviews")
                title = book.getString("title")
                totalRatings = book.getInt("total_ratings")

                val bookModel = BookModel(authors, bookUrl, desc, genres, id, imageUrl, isbn, isbn13, pages, rating, reviews, title, totalRatings)

                book_moreinfo_text.text = bookModel.toString()
                book_moreinfo_text.movementMethod = ScrollingMovementMethod()
                book_moreinfo_text.setTrimExpandedText("show less")
                book_moreinfo_text.setTrimCollapsedText("show more")
                book_moreinfo_text.setTrimLines(5)
                book_moreinfo_text.setColorClickableText(Color.BLUE)

                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            },
            {
                val message = "Something wrong"
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        )
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(context).addToRequestQueue(bookDetailRequest)
    }
    
}