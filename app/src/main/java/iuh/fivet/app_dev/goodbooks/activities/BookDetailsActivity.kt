package iuh.fivet.app_dev.goodbooks.activities

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.INDICATOR_GRAVITY_BOTTOM
import com.squareup.picasso.Picasso
import iuh.fivet.app_dev.goodbooks.R
import iuh.fivet.app_dev.goodbooks.api.APIServiceUpdate
import iuh.fivet.app_dev.goodbooks.api.Api
import iuh.fivet.app_dev.goodbooks.fragments.MoreInfoFragment
import iuh.fivet.app_dev.goodbooks.fragments.OverviewFragment
import iuh.fivet.app_dev.goodbooks.fragments.adapters.ViewPagerAdapter
import iuh.fivet.app_dev.goodbooks.model.UpdateResponse
import iuh.fivet.app_dev.goodbooks.models.get_book.DataBook
import iuh.fivet.app_dev.goodbooks.models.get_book.DataGetBook
import iuh.fivet.app_dev.goodbooks.models.get_book_similar.BookSimilar
import iuh.fivet.app_dev.goodbooks.models.get_book_similar.DataBookSimilar
import iuh.fivet.app_dev.goodbooks.utils.Constants.Companion.BASE_URL
import iuh.fivet.app_dev.goodbooks.utils.Utils.showBook
import iuh.fivet.app_dev.goodbooks.utils.Variables
import kotlinx.android.synthetic.main.activity_book_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BookDetailsActivity : AppCompatActivity() {

    private var bookId = 1
    private val userId = 1
    private var clickChecker:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        try {
//            val sharedPref = this.getSharedPreferences(
//                getString(R.string.preference_file_key), Context.MODE_PRIVATE)
//            bookId = sharedPref.getInt(R.string.preference_file_key.toString(), 1)
            bookId = Variables.bookId
        } catch (e: Exception) {
            Toast.makeText(this@BookDetailsActivity, "ExceptionBookId, $e", Toast.LENGTH_SHORT).show()
            Log.e("bookId_error", "$e")
        }
    }

    // Functions to Setup UI and display API data

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
                    .baseUrl(BASE_URL)
                    .build()
                val apiServiceUpdate = retrofit.create(APIServiceUpdate::class.java)
                val putCall: Call<UpdateResponse> = apiServiceUpdate.updateUserFavourite(userId, bookId)
                putCall.enqueue(object: Callback<UpdateResponse>
                {
                    override fun onResponse(
                        call: Call<UpdateResponse>,
                        response: Response<UpdateResponse>
                    ) {
                        Log.d("UserLIKE_Success", "Response: ${response.body()!!.msg}")
                    }

                    override fun onFailure(call: Call<UpdateResponse>, t: Throwable) {
                        Log.d("UserLIKE_Fail", "Throwable $t")
                    }

                })

            } catch (e: Exception) {
                Log.d("UserLIKE_Exception", "Exception: $e")
            }
        }
    }

    private fun setUpTabs() {
    // TODO: Adapter for Overview and More info tabs
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
    // TODO: Update favourite for User data via API
        try {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            val apiServiceUpdate = retrofit.create(APIServiceUpdate::class.java)
            val putCall: Call<UpdateResponse> = apiServiceUpdate.updateCountAuthorGenre(userId, bookId)
            putCall.enqueue(object: Callback<UpdateResponse>
            {
                override fun onResponse(
                    call: Call<UpdateResponse>,
                    response: Response<UpdateResponse>
                ) {
                    Log.d("UserFavourite_Success", "Response: ${response.body()!!.msg}")
                }

                override fun onFailure(call: Call<UpdateResponse>, t: Throwable) {
                    Log.d("UserFavourite_Fail", "Failure: $t")
                }

            })

        } catch (e: Exception) {
            Log.d("UserFavourite_Exception", "Exception: $e")
        }
    }


    public override fun onStart() {
        super.onStart()

        // VARIABLES: views in BookDetailsActivity
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

        // TODO: get and display 10BookSimilar via API
        val requestGetBookSimilar = Api.retrofitService.getBookSimilar(bookId)
        requestGetBookSimilar.enqueue(object : Callback<DataBookSimilar> {
            override fun onResponse(
                call: Call<DataBookSimilar>,
                response: Response<DataBookSimilar>
            ) {
                val data:ArrayList<BookSimilar> = response.body()!!.data.listBook as ArrayList<BookSimilar>
                var listId: Array<Int> = arrayOf()
                for (i in 0 until data.size) {
                    val b:BookSimilar = data[i]
                    ratingBookSuggest[i].rating = b.rating.toFloat()
                    listId += b.id
                    Picasso.get().load(b.imageUrl).into(bookSuggest[i])
                }

                for (i in listId.indices) {
                    // TODO: make new book view when click
                    bookSuggest[i].setOnClickListener {
//                        Utils.writeContentToFile(applicationContext, "BookId", listId[i].toString())
//                        val intent = Intent(this@BookDetailsActivity, BookDetailsActivity::class.java)
//                        startActivity(intent)
//                        val sharedPref = this@BookDetailsActivity.getPreferences(Context.MODE_PRIVATE)
//                        with (sharedPref.edit()) {
//                            putInt(getString(R.string.preference_file_key), listId[i])
//                            apply()
//                        }
                        showBook(this@BookDetailsActivity, listId[i])
                    }
                }

                // Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<DataBookSimilar>, t: Throwable) {
                Log.d("bookSimilar_Failure", "$t")
            }
        })

        // TODO: get and display information of main book
        val requestGetBookDetail = Api.retrofitService.getBookDetail(bookId)
        requestGetBookDetail.enqueue(object : Callback<DataGetBook> {
            override fun onResponse(call: Call<DataGetBook>, response: Response<DataGetBook>) {
                // val message = "Success get book!"
                val book: DataBook = response.body()!!.data
                val authors: ArrayList<String> = book.authors as ArrayList<String>

                bookAuthors.text = authors.joinToString(", ")
                bookTitle.text = book.title
                Picasso.get().load(book.imageUrl).into(bookPoster)
                bookAverageRating.rating = book.rating.toFloat()
                bookTotalRating.text = book.totalRatings.toString()

                // Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<DataGetBook>, t: Throwable) {
                Log.d("bookInformation error", "$t")
            }
        })

        // TODO: update user rating via API
        val checkRatingBox = findViewById<CheckBox>(R.id.checkRatingBox)
        bookRatingBar.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { _, p1, p2 ->

                val message: String = when (p1.toInt()) {
                    0 -> "No Summit New Rating"
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
                        .baseUrl(BASE_URL)
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

        // TODO: update user favourite
        updateUserFavourite()

        // TODO: make heartButton interactive and update user LIKE
        setUpHeartButton()

        // TODO: make Overview and More info tabs interactive
        setUpTabs()

        Toast.makeText(this, "$bookId", Toast.LENGTH_LONG).show()
    }

}
