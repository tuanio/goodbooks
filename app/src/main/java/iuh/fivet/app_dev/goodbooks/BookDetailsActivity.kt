package iuh.fivet.app_dev.goodbooks

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import iuh.fivet.app_dev.goodbooks.fragments.MoreInfoFragment
import iuh.fivet.app_dev.goodbooks.fragments.OverviewFragment
import iuh.fivet.app_dev.goodbooks.fragments.adapters.ViewPagerAdapter
import org.json.JSONArray
import org.json.JSONObject


class BookDetailsActivity : AppCompatActivity() {

    private var clickChecker:Boolean = false

    var urlApiGetBook = "https://backend-recommender-system-book.up.railway.app/api/get-book/2"
    var urlApiGetBookSimilar = "https://backend-recommender-system-book.up.railway.app/api/get-book-similar/2"

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

        bookTotalRating.setOnClickListener {
            Toast.makeText(this, "Your click on total Rating", Toast.LENGTH_SHORT).show()
        }

        bookRatingBar.setOnClickListener {
            Toast.makeText(this, "Your click on RatingBar", Toast.LENGTH_SHORT).show()
        }


//        val intent = Intent(this, ratingBar::class.java)
//        ratingBar.setOnClickListener(View.OnClickListener { startActivity(intent) })

        val bookSimilarRequest = JsonObjectRequest(Request.Method.GET, urlApiGetBookSimilar, null,
            { response ->
                val message = "Success get book similar images!  \uD83D\uDE41"
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

                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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
                val message = "Success get book!"
                val book:JSONObject = response.getJSONObject("data")

                bookTitle.text = book.getString("title")
                bookAuthors.text = book.getString("authors")
                Picasso.get().load(book.getString("image_url")).into(bookPoster)
                bookAverageRating.rating = (book.getDouble("rating").toFloat())
                bookTotalRating.text = book.getInt("total_ratings").toString()

                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            },
            {
                val message = "Something wrong"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        )
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(bookDetailRequest)

        bookRatingBar.setOnClickListener {
            Toast.makeText(this, "Your click on bookRatingBar", Toast.LENGTH_SHORT).show()
//            val transaction = supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.bookDetailTabLayout, BarRatingFragment())
//            transaction.addToBackStack(null)
//            transaction.commit()
        }


        setUpHeartButton()
        setUpTabs()
        setBookSuggestionImage(imageUrls, bookSuggest)
    }

//        fun setInformation(book_id: String) {
//            BookDataService.getBook_id(
//                et_dataInput.getText().toString(),
//                object : BookDataService.VolleyResponseListener() {
//                    override fun onError(message: String?) {
//                        Toast.makeText(this.Bo, "Something wrong", Toast.LENGTH_SHORT)
//                            .show()
//                    }
//
//                    override fun onResponse(cityID: String) {
//                        Toast.makeText(
//                            this@MainActivity,
//                            "Returned an ID of $cityID",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                })
//        }


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
    }

    private fun setBookSuggestionImage(imageUrls: Array<String>, bookSuggest: Array<ImageButton>) {
        for ((url, book) in (imageUrls zip bookSuggest)) {
            Picasso.get().load(url).into(book)
        }
//        for (book in bookSuggest) {
//            book.setOnClickListener {
//                var C: View = findViewById(R.id.bookRoot)
//                val parent = C.getParent()
//                val index = parent.indexOfChild(C)
//                parent.removeView(C)
//                C = layoutInflater.inflate(optionId, parent, false)
//                parent.addView(C, index)
//            }
//        }
    }

}
