package iuh.fivet.app_dev.goodbooks.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import iuh.fivet.app_dev.goodbooks.R
import iuh.fivet.app_dev.goodbooks.api.Api
import iuh.fivet.app_dev.goodbooks.models.toan.BookRated
import iuh.fivet.app_dev.goodbooks.models.toan.CategoryAdapter
import iuh.fivet.app_dev.goodbooks.models.toan.DataBookRated
import kotlinx.android.synthetic.main.category_item.*
import kotlinx.android.synthetic.main.fragment_my_fav.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyFavFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyFavFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var rcvCategory : RecyclerView;
    private lateinit var categoryAdapter: CategoryAdapter;
    private var arrayBooks: ArrayList<BookRated> = ArrayList();

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
        initView(view,context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    private fun initView(view : View,context: Context){
        val retrofitData = Api.retrofitService.getBookListBookRated(1)
            retrofitData.enqueue(object :Callback<DataBookRated> {
                override fun onResponse(
                    call: Call<DataBookRated>,
                    response: Response<DataBookRated>
                ) {
                    val res = response.body()!!
                    arrayBooks = res.data.listBooks as ArrayList<BookRated>
                    rcvCategory = view.findViewById(R.id.rcv_category)
                    categoryAdapter = CategoryAdapter(context, arrayBooks);

                    val linearLayoutManager: LinearLayoutManager =
                        LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    rcvCategory.layoutManager = linearLayoutManager
                    rcvCategory.adapter = categoryAdapter

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
            MyFavFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }
}