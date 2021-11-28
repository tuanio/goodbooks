package iuh.fivet.app_dev.goodbooks

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button

class DiscoverActivity : AppCompatActivity() {
    private var isHidden: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)

        initFilter()

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

    private fun initFilter() {
        val arrayAuthors = resources.getStringArray(R.array.authors)
        val authorsAdapter = ArrayAdapter(this, R.layout.dropdown_item, arrayAuthors)
        findViewById<AutoCompleteTextView>(R.id.authorsFilter).setAdapter(authorsAdapter)

        val arrayGenres = resources.getStringArray(R.array.genres)
        val genresAdapter = ArrayAdapter(this, R.layout.dropdown_item, arrayGenres)
        findViewById<AutoCompleteTextView>(R.id.genresFilter).setAdapter(genresAdapter)
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