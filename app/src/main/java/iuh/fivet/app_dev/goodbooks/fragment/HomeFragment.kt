package iuh.fivet.app_dev.goodbooks.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import iuh.fivet.app_dev.goodbooks.R
import iuh.fivet.app_dev.goodbooks.api.Api
import iuh.fivet.app_dev.goodbooks.fragment.adapter.TopBookHomeAdapter
import iuh.fivet.app_dev.goodbooks.models.Book
import iuh.fivet.app_dev.goodbooks.models.DataBooksHome
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var top100BookAdapter: TopBookHomeAdapter
    private lateinit var topAuthorBookAdapter: TopBookHomeAdapter
    private lateinit var topGenreBookAdapter: TopBookHomeAdapter
    private lateinit var topSimilarBookAdapter: TopBookHomeAdapter

    private lateinit var top100BookView: RecyclerView
    private lateinit var topBookByAuthorView: RecyclerView
    private lateinit var topBookByGenreView: RecyclerView
    private lateinit var topBookSimilarView: RecyclerView

    private var listTop100Book: ArrayList<Book> = ArrayList()
    private var listTopBookByAuthor: ArrayList<Book> = ArrayList()
    private var listTopBookByGenre: ArrayList<Book> = ArrayList()
    private var listTopBookSimilar: ArrayList<Book> = ArrayList()

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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val context = container!!.context as Context

        bindTop100(view, context)
        bindTopAuthor(view, context)
        bindTopGenre(view, context)
        bindTopSimilar(view, context)

        return view
    }

    private fun bindTop100(view: View, context: Context) {
        top100BookView = view.findViewById(R.id.top100BookScroll)
        val top100Book = Api.retrofitService.getTop100Books()
        top100Book.enqueue(object: Callback<DataBooksHome> {
            override fun onResponse(call: Call<DataBooksHome>, response: Response<DataBooksHome>) {
                val res100 = response.body()!!
                listTop100Book = res100.data.listBooks as ArrayList<Book>

                top100BookAdapter = TopBookHomeAdapter(listTop100Book)
                val llManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                top100BookView.layoutManager = llManager
                top100BookView.adapter = top100BookAdapter
            }
            override fun onFailure(call: Call<DataBooksHome>, t: Throwable) {
                Toast.makeText(context, "Cannot get top 100 books", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun bindTopAuthor(view: View, context: Context) {
        topBookByAuthorView = view.findViewById(R.id.topBookByAuthorScroll)
        val topBookByAuthor = Api.retrofitService.getTopBooksByAuthor(1)
        topBookByAuthor.enqueue(object: Callback<DataBooksHome> {
            override fun onResponse(call: Call<DataBooksHome>, response: Response<DataBooksHome>) {
                val resAuthor = response.body()!!
                listTopBookByAuthor = resAuthor.data.listBooks as ArrayList<Book>

                topAuthorBookAdapter = TopBookHomeAdapter(listTopBookByAuthor)
                val llManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                topBookByAuthorView.layoutManager = llManager
                topBookByAuthorView.adapter = topAuthorBookAdapter
            }
            override fun onFailure(call: Call<DataBooksHome>, t: Throwable) {
                Toast.makeText(context, "Cannot top get author books", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun bindTopGenre(view: View, context: Context) {
        topBookByGenreView = view.findViewById(R.id.topBookByGenreScroll)
        val topBookByGenre = Api.retrofitService.getTopBooksByGenre(1)
        topBookByGenre.enqueue(object: Callback<DataBooksHome> {
            override fun onResponse(call: Call<DataBooksHome>, response: Response<DataBooksHome>) {
                val resGenre = response.body()!!
                listTopBookByGenre = resGenre.data.listBooks as ArrayList<Book>

                topGenreBookAdapter = TopBookHomeAdapter(listTopBookByGenre)
                val llManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                topBookByGenreView.layoutManager = llManager
                topBookByGenreView.adapter = topGenreBookAdapter
            }
            override fun onFailure(call: Call<DataBooksHome>, t: Throwable) {
                Toast.makeText(context, "Cannot top get genre books", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun bindTopSimilar(view: View, context: Context) {
        topBookSimilarView = view.findViewById(R.id.topBookSimilarScroll)
        val topBookSimilar = Api.retrofitService.getTopBooksSimilar(1)
        topBookSimilar.enqueue(object: Callback<DataBooksHome> {
            override fun onResponse(call: Call<DataBooksHome>, response: Response<DataBooksHome>) {
                val resSimilar = response.body()!!
                listTopBookSimilar = resSimilar.data.listBooks as ArrayList<Book>

                topSimilarBookAdapter = TopBookHomeAdapter(listTopBookSimilar)
                val llManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                topBookSimilarView.layoutManager = llManager
                topBookSimilarView.adapter = topSimilarBookAdapter
            }
            override fun onFailure(call: Call<DataBooksHome>, t: Throwable) {
                Toast.makeText(context, "Cannot top get similar books", Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}