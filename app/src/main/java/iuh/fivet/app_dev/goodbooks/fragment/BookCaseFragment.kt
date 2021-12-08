package iuh.fivet.app_dev.goodbooks.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import iuh.fivet.app_dev.goodbooks.R
import iuh.fivet.app_dev.goodbooks.api.Api
import iuh.fivet.app_dev.goodbooks.models.book_rated_favorited.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BookCaseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookCaseFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var rcvCategory : RecyclerView
    private lateinit var bookAdapter: BookRatedAdapter
    private var arrayBooks :MutableList<BookRated> = ArrayList<BookRated>()

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
        val view = inflater.inflate(R.layout.fragment_my_fav, container, false)
        val context = container!!.context as Context
        initView(view,context)
        initBookFavorited(view,context)
        return view
    }

    private fun initView(view : View,context: Context){
        val retrofitData = Api.retrofitService.getBookListBookRated(1)
            retrofitData.enqueue(object :Callback<DataBookRated>{
                    override fun onResponse(
                        call: Call<DataBookRated>,
                        response: Response<DataBookRated>
                    ){
                        val res = response.body()!!
                        arrayBooks = res.data.listBooks as MutableList<BookRated>
                        rcvCategory = view.findViewById(R.id.rcv_book_rated)
                        bookAdapter = BookRatedAdapter(arrayBooks)
                        val linearLayoutManager =
                            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                        rcvCategory.layoutManager = linearLayoutManager
                        rcvCategory.adapter = bookAdapter
                        Log.d("res",arrayBooks.toString())
                    }

                    override fun onFailure(call: Call<DataBookRated>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
    }
    private fun initBookFavorited(view: View,context: Context){
        val retrofitData = Api.retrofitService.getBookListBookFavorited(1)
        retrofitData.enqueue(object :Callback<DataBookRated>{
            override fun onResponse(
                call: Call<DataBookRated>,
                response: Response<DataBookRated>
            ){
                val res = response.body()!!
                arrayBooks = res.data.listBooks as MutableList<BookRated>
                rcvCategory = view.findViewById(R.id.rcv_book_favorited)
                bookAdapter = BookRatedAdapter(arrayBooks)
                val linearLayoutManager =
                    LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                rcvCategory.layoutManager = linearLayoutManager
                rcvCategory.adapter = bookAdapter
                Log.d("res",arrayBooks.toString())
            }

            override fun onFailure(call: Call<DataBookRated>, t: Throwable) {
                TODO("Not yet implemented")
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
         * @return A new instance of fragment MyFavFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BookCaseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }
}