package iuh.fivet.app_dev.goodbooks

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import iuh.fivet.app_dev.goodbooks.api.ApiService
import iuh.fivet.app_dev.goodbooks.models.DataAuthors
import iuh.fivet.app_dev.goodbooks.models.DataBooks
import iuh.fivet.app_dev.goodbooks.models.DataGenres
import iuh.fivet.app_dev.goodbooks.util.Constants.Companion.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

class DiscoverActivity : AppCompatActivity() {
    private var isHidden: Boolean = true
    private lateinit var arrayAuthors: ArrayList<String>
    private lateinit var arrayGenres: ArrayList<String>

    val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)

        initAuthorsFilter()
        initGenresFilter()

        val tvFilter: View = findViewById(R.id.filterLayout)
        val tvSkeleton: View = findViewById(R.id.skeleton)
        hideView(tvFilter)
        showView(tvSkeleton)

        findViewById<Button>(R.id.btnFilter).setOnClickListener {
            isHidden = if (isHidden) {
                hideView(tvSkeleton)
                showView(tvFilter)
                false
            } else {
                hideView(tvFilter)
                showView(tvSkeleton)
                true
            }
        }

        findViewById<Button>(R.id.btnSearch).setOnClickListener {
            hideSoftKeyboard(it)
            searchFilter(tvFilter)
        }
    }

    private fun initAuthorsFilter() {
        val retrofitData = retrofitBuilder.getListAuthors()
        retrofitData.enqueue(object: Callback<DataAuthors> {
            override fun onResponse(call: Call<DataAuthors>, response: Response<DataAuthors>) {
                val res = response.body()!!

                arrayAuthors = ArrayList()
                for (author in res.data.listAuthors) {
                    arrayAuthors.add(author.fullName)
                }

                val authorsAdapter = ArrayAdapter(this@DiscoverActivity, R.layout.dropdown_item, arrayAuthors)
                findViewById<AutoCompleteTextView>(R.id.authorsFilter).setAdapter(authorsAdapter)
            }

            override fun onFailure(call: Call<DataAuthors>, t: Throwable) {
                Toast.makeText(this@DiscoverActivity, "Cannot get list authors", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initGenresFilter() {
        val retrofitData = retrofitBuilder.getListGenres()
        retrofitData.enqueue(object: Callback<DataGenres> {
            override fun onResponse(call: Call<DataGenres>, response: Response<DataGenres>) {
                val res = response.body()!!

                arrayGenres = ArrayList()
                for (genre in res.data.listGenres) {
                    arrayGenres.add(genre.kind)
                }

                val genresAdapter = ArrayAdapter(this@DiscoverActivity, R.layout.dropdown_item, arrayGenres)
                findViewById<AutoCompleteTextView>(R.id.genresFilter).setAdapter(genresAdapter)
            }

            override fun onFailure(call: Call<DataGenres>, t: Throwable) {
                Toast.makeText(this@DiscoverActivity, "Cannot get list genres", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun hideView(view: View) {
        view.visibility = View.INVISIBLE
    }

    private fun showView(view: View) {
        view.visibility = View.VISIBLE
    }

    fun hideSoftKeyboard(v: View) {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }

    private fun searchFilter(v: View) {
        val txtAuthor = findViewById<EditText>(R.id.authorsFilter).text.toString()
        val txtGenre = findViewById<EditText>(R.id.genresFilter).text.toString()

        if (txtAuthor != "" && txtGenre != "") {
//            hideView(v)
//            isHidden = true

            /* Add 1 because author id in server side begin at 1 */
            val authorId = arrayAuthors.indexOf(txtAuthor) + 1
            val genreId = arrayGenres.indexOf(txtGenre) + 1

            val retrofitData = retrofitBuilder.getBooksByAuthorAndGenre(authorId = authorId, genreId = genreId)
            retrofitData.enqueue(object: Callback<DataBooks> {
                override fun onResponse(call: Call<DataBooks>, response: Response<DataBooks>) {
                    val res = response.body()!!
                    val arrayBooks = StringBuilder()

                    for (book in res.data.listBooks) {
                        arrayBooks.append(book.title)
                        arrayBooks.append("\n")
                    }

                    Log.d("res", res.data.numBooks.toString())
                    Log.d("res", arrayBooks.toString())
                }

                override fun onFailure(call: Call<DataBooks>, t: Throwable) {
                    Toast.makeText(this@DiscoverActivity, "Cannot get list books", Toast.LENGTH_SHORT).show()
                }

            })
        } else {
            Toast.makeText(this, "Choose author and genre!", Toast.LENGTH_SHORT).show()
        }
    }
}
