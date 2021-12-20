package iuh.fivet.app_dev.goodbooks.fragments

import android.graphics.Color
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import iuh.fivet.app_dev.goodbooks.R
import iuh.fivet.app_dev.goodbooks.api.Api
import iuh.fivet.app_dev.goodbooks.models.get_book.DataBook
import iuh.fivet.app_dev.goodbooks.models.get_book.DataGetBook
import iuh.fivet.app_dev.goodbooks.utils.GlobalVariables
import kotlinx.android.synthetic.main.fragment_overview.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverviewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val bookId = GlobalVariables.bookId

        val requestGetBookDetail = Api.retrofitService.getBookDetail(bookId)
        requestGetBookDetail.enqueue(object : Callback<DataGetBook> {
            override fun onResponse(call: Call<DataGetBook>, response: Response<DataGetBook>) {

                val book: DataBook = response.body()!!.data
                val bookDesc = book.desc

                book_overview_text.movementMethod = ScrollingMovementMethod()
                book_overview_text.text = bookDesc
                book_overview_text.setTrimExpandedText(" show less")
                book_overview_text.setTrimCollapsedText(" show more")
                book_overview_text.setTrimLength(20)
                book_overview_text.setTrimLines(5)
                book_overview_text.setColorClickableText(Color.BLUE)

            }

            override fun onFailure(call: Call<DataGetBook>, t: Throwable) {
                Log.e("set overview error", "$t")
            }
        })

        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

}