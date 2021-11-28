package iuh.fivet.app_dev.goodbooks

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import iuh.fivet.app_dev.goodbooks.api.ApiService
import iuh.fivet.app_dev.goodbooks.models.DataAuthors
import iuh.fivet.app_dev.goodbooks.models.DataGenres
import iuh.fivet.app_dev.goodbooks.util.Constants.Companion.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DiscoverActivity : AppCompatActivity() {
    private var isHidden: Boolean = true

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
            hideView(tvFilter)
            hideSoftKeyboard(it)
            isHidden = true
        }
    }

    private fun initAuthorsFilter() {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        val retrofitData = retrofitBuilder.getListAuthors()
        retrofitData.enqueue(object: Callback<DataAuthors> {
            override fun onResponse(call: Call<DataAuthors>, response: Response<DataAuthors>) {
                val res = response.body()!!
                val arrayAuthors = ArrayList<String>()

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
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        val retrofitData = retrofitBuilder.getListGenres()
        retrofitData.enqueue(object: Callback<DataGenres> {
            override fun onResponse(call: Call<DataGenres>, response: Response<DataGenres>) {
                val res = response.body()!!
                val arrayGenres = ArrayList<String>()

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
}
