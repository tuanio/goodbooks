package iuh.fivet.app_dev.goodbooks.activities

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.INDICATOR_GRAVITY_BOTTOM
import com.squareup.picasso.Picasso
import iuh.fivet.app_dev.goodbooks.R
import iuh.fivet.app_dev.goodbooks.api.APIServiceUpdate
import iuh.fivet.app_dev.goodbooks.fragments.MoreInfoFragment
import iuh.fivet.app_dev.goodbooks.fragments.OverviewFragment
import iuh.fivet.app_dev.goodbooks.fragments.adapters.ViewPagerAdapter
import iuh.fivet.app_dev.goodbooks.model.UpdateResponse
import iuh.fivet.app_dev.goodbooks.api.MySingleton
import kotlinx.android.synthetic.main.activity_book_details.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//const val USER_ID = "iuh.fivet.app_dev.goodbooks.USER_ID"
//const val BOOK_ID = "iuh.fivet.app_dev.goodbooks.BOOK_ID"

class BookDetailsActivity : AppCompatActivity() {

    private var bookId = 23
    private val userId = 1
    private val baseURL = "https://backend-recommender-system-book.up.railway.app"
    private var clickChecker:Boolean = false
    private var urlApiGetBookSimilar = "$baseURL/api/get-book-similar/$bookId"

    var urlApiGetBook = "$baseURL/api/get-book/$bookId"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        // subview in activity_book_details
        val bookRatingBar = findViewById<RatingBar>(R.id.your_rating_bar)
        val bookTitle = findViewById<TextView>(R.id.item_book_title)
        val bookAuthors = findViewById<TextView>(R.id.item_book_author)
        val bookTotalRating = findViewById<TextView>(R.id.totalRating)
        val bookAverageRating = findViewById<RatingBar>(R.id.averageRatingStar)
        val bookPoster = findViewById<ImageView>(R.id.item_book_img)

        // 10 book suggested
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
        // rating of 10 book suggest
        val ratingBookSuggest = arrayOf(
            findViewById(R.id.averageRatingStar1),
            findViewById(R.id.averageRatingStar2),
            findViewById(R.id.averageRatingStar3),
            findViewById(R.id.averageRatingStar4),
            findViewById(R.id.averageRatingStar5),
            findViewById(R.id.averageRatingStar6),
            findViewById(R.id.averageRatingStar7),
            findViewById(R.id.averageRatingStar8),
            findViewById(R.id.averageRatingStar9),
            findViewById<RatingBar>(R.id.averageRatingStar10)
        )
        // empty imagesUrls
        var imageUrls:Array<String> = arrayOf()

        // GET 10 BOOK SUGGESTED
        val bookSimilarRequest = JsonObjectRequest(Request.Method.GET, urlApiGetBookSimilar, null,
            { response ->
                // val message = "Success get book similar images!  \uD83D\uDE41"
                val book:JSONObject = response.getJSONObject("data")
                val data:JSONArray = book.getJSONArray("list_books")
                var listId: Array<Int> = arrayOf()
                for (i in 0 until data.length()) {
                    val jsonObject:JSONObject = data.getJSONObject(i)
                    imageUrls +=  jsonObject.getString("image_url")
                    ratingBookSuggest[i].rating = jsonObject.getDouble("rating").toFloat()
                    listId += jsonObject.getInt("id")
                }
                @Suppress("NAME_SHADOWING")
                for ((url, book) in (imageUrls zip bookSuggest)) {
                    Picasso.get().load(url).into(book)
                }

                for (i in listId.indices) {
                    bookSuggest[i].setOnClickListener {
//                        val intent = Intent(this, BookDetailsActivity::class.java)
//                        BookDetailsActivity().bookId = listId[i]
                        val intent = BookDetailsActivity().apply {
                            bookId = listId[i]
                        }.intent
                        startActivity(intent)
//                        finish()
                    }
                }

                // Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            },
            {
                val message = "Something wrong"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        )
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(bookSimilarRequest)

        // GET BOOK DETAIL INFORMATION
        val bookDetailRequest = JsonObjectRequest(Request.Method.GET, urlApiGetBook, null,
            { response ->
                // val message = "Success get book!"
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

                // Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            },
            {
                val message = "Something wrong"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        )
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(bookDetailRequest)

        // UPDATE USER RATING
        val checkRatingBox = findViewById<CheckBox>(R.id.checkRatingBox)
        bookRatingBar.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { _, p1, p2 ->

                val message: String = when (p1.toInt()) {
                    0 -> "No Summit Rating"
                    1 -> "A bad book \uD83D\uDE41 "
                    2 -> "Not interest book \uD83D\uDE15 "
                    3 -> "A few interest \uD83D\uDE09 "
                    4 -> "A nice book \uD83D\uDE03 "
                    5 -> "Good read \uD83D\uDE0D "
                    else -> ""
                }

                try {
                    val retrofit = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(baseURL)
                        .build()
                    val apiServiceUpdate = retrofit.create(APIServiceUpdate::class.java)
                    val putCall: Call<UpdateResponse> = apiServiceUpdate.updateUserRating(userId, bookId, p1.toInt())
                    putCall.enqueue(object: Callback<UpdateResponse>
                    {
                        override fun onResponse(
                            call: Call<UpdateResponse>,
                            response: Response<UpdateResponse>
                        ) {
                            Toast.makeText(this@BookDetailsActivity, "Response, ${p1.toInt()}, ${response.body()!!.msg}", Toast.LENGTH_LONG).show()
                        }

                        override fun onFailure(call: Call<UpdateResponse>, t: Throwable) {
                            Toast.makeText(this@BookDetailsActivity, "Failure, $t", Toast.LENGTH_LONG).show()
                        }

                    })

                    Toast.makeText(this@BookDetailsActivity, message, Toast.LENGTH_SHORT).show()
                    checkRatingBox.isChecked = p2
                    checkRatingBox.setOnClickListener {
                        if (!checkRatingBox.isChecked) {
                            bookRatingBar.rating = 0.toFloat()
                            putCall.cancel()
                        }
                    }

                } catch (e: Exception) {
                    Toast.makeText(this@BookDetailsActivity, "$e", Toast.LENGTH_SHORT).show()
                }
            }

        // UPDATE USER FAVOURITE
        updateUserFavourite()

        // Setup something
        setUpHeartButton()
        setUpTabs()
        Toast.makeText(this, "$bookId", Toast.LENGTH_LONG).show()
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

            try {
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseURL)
                    .build()
                val apiServiceUpdate = retrofit.create(APIServiceUpdate::class.java)
                val putCall: Call<UpdateResponse> = apiServiceUpdate.updateUserFavourite(userId, bookId)
                putCall.enqueue(object: Callback<UpdateResponse>
                {
                    override fun onResponse(
                        call: Call<UpdateResponse>,
                        response: Response<UpdateResponse>
                    ) {
                        Toast.makeText(this@BookDetailsActivity, "Response: ${response.body()!!.msg}", Toast.LENGTH_LONG).show()
                    }

                    override fun onFailure(call: Call<UpdateResponse>, t: Throwable) {
                        Toast.makeText(this@BookDetailsActivity, "Failure: $t", Toast.LENGTH_LONG).show()
                    }

                })

            } catch (e: Exception) {
                Toast.makeText(this@BookDetailsActivity, "$e", Toast.LENGTH_SHORT).show()
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

    private fun updateUserFavourite() {
        try {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseURL)
                .build()
            val apiServiceUpdate = retrofit.create(APIServiceUpdate::class.java)
            val putCall: Call<UpdateResponse> = apiServiceUpdate.updateCountAuthorGenre(userId, bookId)
            putCall.enqueue(object: Callback<UpdateResponse>
            {
                override fun onResponse(
                    call: Call<UpdateResponse>,
                    response: Response<UpdateResponse>
                ) {
                    Toast.makeText(this@BookDetailsActivity, "Response: ${response.body()!!.msg}", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<UpdateResponse>, t: Throwable) {
                    Toast.makeText(this@BookDetailsActivity, "Failure: $t", Toast.LENGTH_LONG).show()
                }

            })

        } catch (e: Exception) {
            Toast.makeText(this@BookDetailsActivity, "$e", Toast.LENGTH_SHORT).show()
        }
    }

}
