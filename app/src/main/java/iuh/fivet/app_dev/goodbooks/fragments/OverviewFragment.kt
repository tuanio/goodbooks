package iuh.fivet.app_dev.goodbooks.fragments

import android.graphics.Color
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import iuh.fivet.app_dev.goodbooks.activities.BookDetailsActivity
import iuh.fivet.app_dev.goodbooks.api.MySingleton
import iuh.fivet.app_dev.goodbooks.R
import kotlinx.android.synthetic.main.fragment_overview.*
import org.json.JSONObject

class OverviewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val urlApiGetBook = BookDetailsActivity().urlApiGetBook

        val bookDetailRequest = JsonObjectRequest(
            Request.Method.GET, urlApiGetBook, null,
            { response ->
                val message = "Success get book!"
                val book: JSONObject = response.getJSONObject("data")

                val bookDesc = book.getString("desc")
                Log.e("LoiDesc", bookDesc)

                book_overview_text.movementMethod = ScrollingMovementMethod()
                book_overview_text.text = bookDesc
                book_overview_text.setTrimExpandedText(" show less")
                book_overview_text.setTrimCollapsedText(" show more")
                book_overview_text.setTrimLength(20)
                book_overview_text.setTrimLines(5)
                book_overview_text.setColorClickableText(Color.BLUE)

                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            },
            {
                val message = "Something wrong"
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        )
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(context).addToRequestQueue(bookDetailRequest)

        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

}