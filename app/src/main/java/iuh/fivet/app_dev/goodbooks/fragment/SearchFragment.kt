package iuh.fivet.app_dev.goodbooks.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import iuh.fivet.app_dev.goodbooks.R
import iuh.fivet.app_dev.goodbooks.RecyclerAdapter
import iuh.fivet.app_dev.goodbooks.api.Api
import iuh.fivet.app_dev.goodbooks.models.Book
import iuh.fivet.app_dev.goodbooks.models.DataAuthors
import iuh.fivet.app_dev.goodbooks.models.DataBooks
import iuh.fivet.app_dev.goodbooks.models.DataGenres
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var isHidden: Boolean? = null
    private lateinit var arrayAuthors: ArrayList<String>
    private lateinit var arrayGenres: ArrayList<String>
    private lateinit var recycleView: RecyclerView
    private lateinit var divider: RecyclerView.ItemDecoration
    private lateinit var recyclerAdapter: RecyclerAdapter
    private var arrayBooks: ArrayList<Book> = ArrayList()
    private val searchFragmentTag = "SearchFragmentTag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        val context = container!!.context as Context

        initAuthorsFilter(view, context)
        initGenresFilter(view, context)
        initRecycleView(view)


        val tvFilter: View = view.findViewById(R.id.filterLayout)
        val tvSkeleton: View = view.findViewById(R.id.skeleton)
        isHidden = true
        divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

        hideView(tvFilter)
        showView(tvSkeleton)

        view.findViewById<Button>(R.id.btnFilter).setOnClickListener {
            isHidden = if (isHidden == true) {

                hideView(tvSkeleton)
                hideView(recycleView)
                showView(tvFilter)
                false
            } else {
                hideView(tvFilter)
                showView(tvSkeleton)
                true
            }

        }

        view.findViewById<Button>(R.id.btnSearch).setOnClickListener {
            searchFilter(view, context, tvFilter)
        }

        return view
    }

    private fun initAuthorsFilter(view: View, context: Context) {
        val retrofitData = Api.retrofitService.getListAuthors()
        retrofitData.enqueue(object: Callback<DataAuthors> {
            override fun onResponse(call: Call<DataAuthors>, response: Response<DataAuthors>) {
                val res = response.body()!!

                arrayAuthors = ArrayList()
                for (author in res.data.listAuthors) {
                    arrayAuthors.add(author.fullName)
                }

                val authorsAdapter = ArrayAdapter(context, R.layout.dropdown_item, arrayAuthors)
                view.findViewById<AutoCompleteTextView>(R.id.authorsFilter).setAdapter(authorsAdapter)
            }

            override fun onFailure(call: Call<DataAuthors>, t: Throwable) {
                Toast.makeText(context, "Cannot get list authors", Toast.LENGTH_SHORT).show()
                Log.e(searchFragmentTag, t.toString())
            }
        })
    }

    private fun initGenresFilter(view: View, context: Context) {
        val retrofitData = Api.retrofitService.getListGenres()
        retrofitData.enqueue(object: Callback<DataGenres> {
            override fun onResponse(call: Call<DataGenres>, response: Response<DataGenres>) {
                val res = response.body()!!

                arrayGenres = ArrayList()
                for (genre in res.data.listGenres) {
                    arrayGenres.add(genre.kind)
                }

                val genresAdapter = ArrayAdapter(context,
                    R.layout.dropdown_item, arrayGenres)
                view.findViewById<AutoCompleteTextView>(R.id.genresFilter).setAdapter(genresAdapter)
            }

            override fun onFailure(call: Call<DataGenres>, t: Throwable) {
                Toast.makeText(context, "Cannot get list genres", Toast.LENGTH_SHORT).show()
                Log.e(searchFragmentTag, t.toString())
            }
        })
    }

    private fun initRecycleView(view: View) {
        recycleView = view.findViewById(R.id.rvBooks)
    }

    private fun hideView(view: View) {
        view.visibility = View.INVISIBLE
    }

    private fun showView(view: View) {
        view.visibility = View.VISIBLE
    }

    private fun searchFilter(view: View, context: Context, tv: View) {
        val txtAuthor = view.findViewById<EditText>(R.id.authorsFilter).text.toString()
        val txtGenre = view.findViewById<EditText>(R.id.genresFilter).text.toString()

        if (txtAuthor != "" && txtGenre != "") {
            hideView(tv)
            isHidden = true

            /* Add 1 because author id in server side begin at 1 */
            val authorId = arrayAuthors.indexOf(txtAuthor) + 1
            val genreId = arrayGenres.indexOf(txtGenre) + 1

            val retrofitData = Api.retrofitService.getBooksByAuthorAndGenre(authorId, genreId)
            retrofitData.enqueue(object: Callback<DataBooks> {
                override fun onResponse(call: Call<DataBooks>, response: Response<DataBooks>) {
                    val res = response.body()!!
                    arrayBooks = res.data.listBook as ArrayList<Book>
                    recyclerAdapter = RecyclerAdapter(arrayBooks)
                    val llManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

                    recycleView.layoutManager = llManager
                    recycleView.addItemDecoration(divider)
                    recycleView.adapter = recyclerAdapter
                    showView(recycleView)
                }

                override fun onFailure(call: Call<DataBooks>, t: Throwable) {
                    Toast.makeText(context, "Cannot get list books", Toast.LENGTH_SHORT).show()
                    Log.e(searchFragmentTag, t.toString())
                }
            })
        } else {
            Toast.makeText(context, "Choose author and genre!", Toast.LENGTH_SHORT).show()
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}