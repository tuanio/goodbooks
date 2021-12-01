package iuh.fivet.app_dev.goodbooks

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.INDICATOR_GRAVITY_BOTTOM
import com.squareup.picasso.Picasso
import iuh.fivet.app_dev.goodbooks.fragments.MoreInfoFragment
import iuh.fivet.app_dev.goodbooks.fragments.OverviewFragment
import iuh.fivet.app_dev.goodbooks.fragments.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_book_details.*
import org.json.JSONArray
import org.json.JSONObject


class BookDetailsActivity : AppCompatActivity() {

    private var clickChecker:Boolean = false

    var urlApiGetBook = "https://backend-recommender-system-book.up.railway.app/api/get-book/23"
    var urlApiGetBookSimilar = "https://backend-recommender-system-book.up.railway.app/api/get-book-similar/23"
    var urlApiUpdateUserRating = "https://backend-recommender-system-book.up.railway.app/api/update-user-rating/1/2/5"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        val bookRatingBar = findViewById<RatingBar>(R.id.your_rating_bar)
        val bookTitle = findViewById<TextView>(R.id.item_book_title)
        val bookAuthors = findViewById<TextView>(R.id.item_book_author)
        val bookTotalRating = findViewById<TextView>(R.id.totalRating)
        val bookAverageRating = findViewById<RatingBar>(R.id.averageRatingStar)
        val bookPoster = findViewById<ImageView>(R.id.item_book_img)

//        val view = LayoutInflater.from(applicationContext).inflate(R.layout.fragment_overview, null)
//        val bookDesc = view.findViewById<TextView>(R.id.book_overview_text) Hỏi ae cách set View

        val bookSuggest = arrayOf(
            findViewById(R.id.bookSuggest1),
            findViewById(R.id.bookSuggest2),
            findViewById(R.id.bookSuggest3),
            findViewById(R.id.bookSuggest4),
            findViewById(R.id.bookSuggest5),
            findViewById(R.id.bookSuggest6),
            findViewById(R.id.bookSuggest7),
            findViewById(R.id.bookSuggest8),
            findViewById(R.id.bookSuggest9),
            findViewById<ImageButton>(R.id.bookSuggest10)
        )
        var imageUrls:Array<String> = arrayOf()

        val bookSimilarRequest = JsonObjectRequest(Request.Method.GET, urlApiGetBookSimilar, null,
            { response ->
//                val message = "Success get book similar images!  \uD83D\uDE41"
                val book:JSONObject = response.getJSONObject("data")
                val data:JSONArray = book.getJSONArray("list_books")
                for (i in 0 until data.length()) {
                    val imageLink:JSONObject = data.getJSONObject(i)
                    imageUrls +=  imageLink.getString("image_url")
                }
                @Suppress("NAME_SHADOWING")
                for ((url, book) in (imageUrls zip bookSuggest)) {
                    Picasso.get().load(url).into(book)
                }

//                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            },
            {
                val message = "Something wrong"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        )
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(bookSimilarRequest)

        val bookDetailRequest = JsonObjectRequest(Request.Method.GET, urlApiGetBook, null,
            { response ->
//                val message = "Success get book!"
                val book:JSONObject = response.getJSONObject("data")

                var authors = ""
                val listAuthors = book.getJSONArray("authors")
                for (author in 0 until listAuthors.length()) {
                    val au = listAuthors.get(author)
                    authors += "$au, "
                }
                bookAuthors.text = authors.dropLast(2)
                bookTitle.text = book.getString("title")
                Picasso.get().load(book.getString("image_url")).into(bookPoster)
                bookAverageRating.rating = (book.getDouble("rating").toFloat())
                bookTotalRating.text = book.getInt("total_ratings").toString()

//                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            },
            {
                val message = "Something wrong"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        )
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(bookDetailRequest)

        val checkRatingBox = findViewById<CheckBox>(R.id.checkRatingBox)
        bookRatingBar.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { _, p1, p2 ->

                val message: String = when (p1.toInt()) {
                    0 -> "No Summit Rating"
                    1 -> "A bad book \uD83D\uDE41 "
                    2 -> "Not interest book \uD83D\uDE15 "
                    3 -> "A few interest \uD83D\uDE09 "
                    4 -> "A nice book \uD83D\uDE03 "
                    5 -> "Goodread \uD83D\uDE0D "
                    else -> ""
                }



//                val parameters = mutableMapOf<Any?, Any?>()
//                parameters["user_id"] = 1
//                parameters["book_id"] = 2
//                parameters["user_rating"] = 5
//                val jsonObject = JSONObject(parameters)
//                Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_LONG).show()
//
//                // Volley post request with parameters
//                val updateUserRatingRequest = JsonObjectRequest(Request.Method.POST,urlApiUpdateUserRating, jsonObject,
//                    { response ->
//                        // Process the json
//                        try {
//                            Toast.makeText(this, "Response: $response", Toast.LENGTH_SHORT).show()
//                        }catch (e:Exception){
//                            Toast.makeText(this, "Exception: $e", Toast.LENGTH_SHORT).show()
//                        }
//
//                    },
//                    {
//                        // Error in request
//                        Toast.makeText(this, "Volley error: $it", Toast.LENGTH_SHORT).show()
//                    })
//
//
//                // Volley request policy, only one time request to avoid duplicate transaction
//                updateUserRatingRequest.retryPolicy = DefaultRetryPolicy(
//                    DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
//                    // 0 means no retry
//                    0, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
//                    1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
//                )
//
//                MySingleton.getInstance(this).addToRequestQueue(updateUserRatingRequest)

                Toast.makeText(this@BookDetailsActivity, message, Toast.LENGTH_SHORT).show()
                checkRatingBox.isChecked = p2
                checkRatingBox.setOnClickListener {
                    if (!checkRatingBox.isChecked) {
                        bookRatingBar.rating = 0.toFloat()
                    }
                }
            }

        // Setup something
        setUpHeartButton()
        setUpTabs()
        setBookSuggestionImage(imageUrls, bookSuggest)
    }

    private fun setUpHeartButton() {
        val heartBtn = findViewById<Button>(R.id.heartBnt)
        heartBtn.setOnClickListener {
            clickChecker = clickChecker xor true
            if (clickChecker) {
                heartBtn.setBackgroundResource(R.drawable.heart_on)
            }
            else {
                heartBtn.setBackgroundResource(R.drawable.heart_off)
            }
        }
    }

    private fun setUpTabs() {
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val tabs = findViewById<TabLayout>(R.id.bookDetailTabLayout)
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(OverviewFragment(), "Overview")
        adapter.addFragment(MoreInfoFragment(), "More info")

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
        tabs.setSelectedTabIndicatorGravity(INDICATOR_GRAVITY_BOTTOM)
    }

    private fun setBookSuggestionImage(imageUrls: Array<String>, bookSuggest: Array<ImageButton>) {
        for ((url, book) in (imageUrls zip bookSuggest)) {
            Picasso.get().load(url).into(book)
        }
    }

}
