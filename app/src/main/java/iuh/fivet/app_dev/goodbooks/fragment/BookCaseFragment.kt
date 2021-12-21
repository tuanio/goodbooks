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
import iuh.fivet.app_dev.goodbooks.utils.GlobalVariables
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class BookCaseFragment : Fragment() {

    private lateinit var rcvCategory : RecyclerView
    private lateinit var bookAdapter: BookRatedAdapter
    private var arrayBooks :MutableList<BookRated> = ArrayList<BookRated>()
    private var userId by Delegates.notNull<Int>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userId = GlobalVariables.userId

        val view = inflater.inflate(R.layout.fragment_my_fav, container, false)
        val context = container!!.context as Context
        initView(view,context)
        initBookFavorited(view,context)
        initBookHistory(view,context)
        return view
    }

    private fun initView(view : View,context: Context){
        val retrofitData = Api.retrofitService.getBookListBookRated(userId)
            retrofitData.enqueue(object :Callback<DataBookRated>{
                    override fun onResponse(
                        call: Call<DataBookRated>,
                        response: Response<DataBookRated>
                    ){  Log.d("bookcasefragment", response.body()?.statusCode.toString())
                        val res = response.body()!!

                        arrayBooks = res.data.listBooks as MutableList<BookRated>
                        rcvCategory = view.findViewById(R.id.rcv_book_rated)
                        bookAdapter = BookRatedAdapter(context,arrayBooks)
                        val linearLayoutManager =
                            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                        rcvCategory.layoutManager = linearLayoutManager
                        rcvCategory.adapter = bookAdapter
                        Log.d("res",arrayBooks.toString())
                    }

                    override fun onFailure(call: Call<DataBookRated>, t: Throwable) {
                        Log.d("bookcasefragmentfail",t.toString())
                    }
                })
    }
    private fun initBookFavorited(view: View,context: Context){
        val retrofitData = Api.retrofitService.getBookListBookFavorited(userId)
        retrofitData.enqueue(object :Callback<DataBookRated>{
            override fun onResponse(
                call: Call<DataBookRated>,
                response: Response<DataBookRated>
            ){  Log.d("bookcasefragment", response.body()?.statusCode.toString())
                val res = response.body()!!
                arrayBooks = res.data.listBooks as MutableList<BookRated>
                rcvCategory = view.findViewById(R.id.rcv_book_favorited)
                bookAdapter = BookRatedAdapter(context,arrayBooks)
                val linearLayoutManager =
                    LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                rcvCategory.layoutManager = linearLayoutManager
                rcvCategory.adapter = bookAdapter
                Log.d("res",arrayBooks.toString())
            }

            override fun onFailure(call: Call<DataBookRated>, t: Throwable) {
                Log.d("bookcasefragmentfail",t.toString())
            }
        })
    }
    private fun initBookHistory(view: View,context: Context){
        val retrofitData = Api.retrofitService.getBookListBookHistory(userId)
        retrofitData.enqueue(object :Callback<DataBookRated>{
            override fun onResponse(
                call: Call<DataBookRated>,
                response: Response<DataBookRated>
            ){  Log.d("bookcasefragment", response.body()?.statusCode.toString())
                val res = response.body()!!
                arrayBooks = res.data.listBooks as MutableList<BookRated>
                rcvCategory = view.findViewById(R.id.rcv_book_history)
                bookAdapter = BookRatedAdapter(context,arrayBooks)
                val linearLayoutManager =
                    LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                rcvCategory.layoutManager = linearLayoutManager
                rcvCategory.adapter = bookAdapter
                Log.d("res",arrayBooks.toString())
            }

            override fun onFailure(call: Call<DataBookRated>, t: Throwable) {
                Log.d("bookcasefragmentfail",t.toString())
            }
        })
    }

}