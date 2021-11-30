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
import org.json.JSONObject
import android.text.method.ScrollingMovementMethod
import kotlinx.android.synthetic.main.fragment_more_info.*


class MoreInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val urlApiGetBook = BookDetailsActivity().urlApiGetBook

        var authors = ""
        var bookUrl: String?
        var desc: String?
        var genres = ""
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

                val listAuthors = book.getJSONArray("authors")
                for (author in 0 until listAuthors.length()) {
                    val au = listAuthors.get(author)
                    authors += "$au, "
                }

                val listGenres = book.getJSONArray("genres")
                for (genre in 0 until listGenres.length()) {
                    val gen = listGenres.get(genre)
                    genres += "$gen, "
                }

                bookUrl = book.getString("book_url")
                desc = book.getString("desc")
                id = book.getInt("id")
                imageUrl = book.getString("image_url")
                isbn = book.getString("isbn")
                isbn13 = book.getString("isbn13")
                pages = book.getInt("pages")
                rating = book.getDouble("rating").toFloat()
                reviews = book.getInt("reviews")
                title = book.getString("title")
                totalRatings = book.getInt("total_ratings")

                val bookInfo = """
                    Authors 👉 $authors
                    
                    Book's link 👉 $bookUrl
                    
                    Genres 👉 $genres
                    
                    isbn 👉 $isbn
                    
                    Pages 👉 $pages
                    
                    Rating 👉 $rating
                    
                    Reviews 👉 $reviews
                    
                    Total_ratings 👉 $totalRatings
                    """.trimIndent()
                book_moreinfo_text.movementMethod = ScrollingMovementMethod()
                book_moreinfo_text.text = bookInfo
                book_moreinfo_text.setTrimExpandedText(" show less")
                book_moreinfo_text.setTrimCollapsedText(" show more")
                book_moreinfo_text.setTrimLength(20)
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

        return inflater.inflate(R.layout.fragment_more_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    
}