package iuh.fivet.app_dev.goodbooks.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import iuh.fivet.app_dev.goodbooks.R
import iuh.fivet.app_dev.goodbooks.api.Api
import iuh.fivet.app_dev.goodbooks.fragment.adapter.TopBookHomeAdapter
import iuh.fivet.app_dev.goodbooks.models.home_data.BookHome
import iuh.fivet.app_dev.goodbooks.models.home_data.DataBooksHome
import iuh.fivet.app_dev.goodbooks.models.home_data.DataTop1Book
import iuh.fivet.app_dev.goodbooks.utils.Utils
import iuh.fivet.app_dev.goodbooks.utils.GlobalVariables
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class HomeFragment : Fragment() {

    private lateinit var top100BookAdapter: TopBookHomeAdapter
    private lateinit var topAuthorBookAdapter: TopBookHomeAdapter
    private lateinit var topGenreBookAdapter: TopBookHomeAdapter
    private lateinit var topSimilarBookAdapter: TopBookHomeAdapter

    private lateinit var top100BookView: RecyclerView
    private lateinit var topBookByAuthorView: RecyclerView
    private lateinit var topBookByGenreView: RecyclerView
    private lateinit var topBookSimilarView: RecyclerView

    private var listTop100Book: ArrayList<BookHome> = ArrayList()
    private var listTopBookByAuthor: ArrayList<BookHome> = ArrayList()
    private var listTopBookByGenre: ArrayList<BookHome> = ArrayList()
    private var listTopBookSimilar: ArrayList<BookHome> = ArrayList()

    private var userId by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        userId = GlobalVariables.userId

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val context = container!!.context as Context

        bindTheBestBook(view, context)
        bindTop100(view, context)
        bindTopAuthor(view, context)
        bindTopGenre(view, context)
        bindTopSimilar(view, context)

        return view
    }

    private fun bindTheBestBook(view: View, context: Context) {

        val theBestBook = Api.retrofitService.getTheBestBooks()
        theBestBook.enqueue(object: Callback<DataTop1Book> {
            override fun onResponse(call: Call<DataTop1Book>, response: Response<DataTop1Book>) {
                val resBestBook = response.body()!!.data
                val theBestBookTotalRating: TextView = view.findViewById(R.id.theBestBookTotalRating)
                val theBestBookTotalReview: TextView = view.findViewById(R.id.theBestBookTotalReview)
                val theBestBookGenre: TextView = view.findViewById(R.id.theBestBookGenre)
                val theBestBookAuthor: TextView = view.findViewById(R.id.theBestBookAuthor)
                val theBestBookRating: RatingBar = view.findViewById(R.id.theBestBookRating)
                val theBestBookImgUrl: ImageView = view.findViewById(R.id.theBestBookImgUrl)
                val theBestBookDesc: TextView = view.findViewById(R.id.theBestBookDesc)
                val theBestBookTitle: TextView = view.findViewById(R.id.theBestBookTitle)

                theBestBookTotalRating.text = resBestBook.total_ratings.toString()
                theBestBookTotalReview.text = resBestBook.reviews.toString()
                theBestBookGenre.text = resBestBook.genres.toString().replace("[","").replace("]","")
                theBestBookAuthor.text = resBestBook.authors.toString().replace("[","").replace("]","")
                theBestBookRating.rating = resBestBook.rating
                theBestBookDesc.text = resBestBook.desc
                theBestBookTitle.text = resBestBook.title
                Picasso.get().load(resBestBook.image_url).into(theBestBookImgUrl)

                theBestBookImgUrl.setOnClickListener {
                    Utils.showBook(context, resBestBook.id)
                }
            }
            override fun onFailure(call: Call<DataTop1Book>, t: Throwable) {
                Toast.makeText(context, "get the best book failed", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun bindTop100(view: View, context: Context) {
        top100BookView = view.findViewById(R.id.top100BookScroll)
        val top100Book = Api.retrofitService.getTop100Books()
        top100Book.enqueue(object: Callback<DataBooksHome> {
            override fun onResponse(call: Call<DataBooksHome>, response: Response<DataBooksHome>) {
                val res100 = response.body()!!
                listTop100Book = res100.data.listBooks as ArrayList<BookHome>

                top100BookAdapter = TopBookHomeAdapter(context, listTop100Book)
                val llManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                top100BookView.layoutManager = llManager
                top100BookView.adapter = top100BookAdapter
            }
            override fun onFailure(call: Call<DataBooksHome>, t: Throwable) {
                Toast.makeText(context, "get top 100 books failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun bindTopAuthor(view: View, context: Context) {
        topBookByAuthorView = view.findViewById(R.id.topBookByAuthorScroll)
        val topBookByAuthor = Api.retrofitService.getTopBooksByAuthor(userId)
        topBookByAuthor.enqueue(object: Callback<DataBooksHome> {
            override fun onResponse(call: Call<DataBooksHome>, response: Response<DataBooksHome>) {
                val resAuthor = response.body()!!
                listTopBookByAuthor = resAuthor.data.listBooks as ArrayList<BookHome>

                topAuthorBookAdapter = TopBookHomeAdapter(context, listTopBookByAuthor)
                val llManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                topBookByAuthorView.layoutManager = llManager
                topBookByAuthorView.adapter = topAuthorBookAdapter
            }
            override fun onFailure(call: Call<DataBooksHome>, t: Throwable) {
                Toast.makeText(context, "get top books by author failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun bindTopGenre(view: View, context: Context) {
        topBookByGenreView = view.findViewById(R.id.topBookByGenreScroll)
        val topBookByGenre = Api.retrofitService.getTopBooksByGenre(userId)
        topBookByGenre.enqueue(object: Callback<DataBooksHome> {
            override fun onResponse(call: Call<DataBooksHome>, response: Response<DataBooksHome>) {
                val resGenre = response.body()!!
                listTopBookByGenre = resGenre.data.listBooks as ArrayList<BookHome>

                topGenreBookAdapter = TopBookHomeAdapter(context, listTopBookByGenre)
                val llManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                topBookByGenreView.layoutManager = llManager
                topBookByGenreView.adapter = topGenreBookAdapter
            }
            override fun onFailure(call: Call<DataBooksHome>, t: Throwable) {
                Toast.makeText(context, "get top books by genre failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun bindTopSimilar(view: View, context: Context) {
        topBookSimilarView = view.findViewById(R.id.topBookSimilarScroll)
        val topBookSimilar = Api.retrofitService.getTopBooksSimilar(userId)
        topBookSimilar.enqueue(object: Callback<DataBooksHome> {
            override fun onResponse(call: Call<DataBooksHome>, response: Response<DataBooksHome>) {
                val resSimilar = response.body()!!
                listTopBookSimilar = resSimilar.data.listBooks as ArrayList<BookHome>

                topSimilarBookAdapter = TopBookHomeAdapter(context, listTopBookSimilar)
                val llManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                topBookSimilarView.layoutManager = llManager
                topBookSimilarView.adapter = topSimilarBookAdapter
            }
            override fun onFailure(call: Call<DataBooksHome>, t: Throwable) {
                Toast.makeText(context, "get top books similar rating failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

}