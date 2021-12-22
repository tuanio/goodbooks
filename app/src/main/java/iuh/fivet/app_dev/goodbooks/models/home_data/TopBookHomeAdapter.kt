package iuh.fivet.app_dev.goodbooks.models.home_data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import iuh.fivet.app_dev.goodbooks.R
import iuh.fivet.app_dev.goodbooks.utils.Utils.showBook

class TopBookHomeAdapter(var context: Context, private val listBooks: List<BookHome>): RecyclerView.Adapter<TopBookHomeAdapter.BookViewHolder>() {
    class BookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.topBookImgUrl)
        val title: TextView = itemView.findViewById(R.id.topBookTitle)
        val rating: RatingBar = itemView.findViewById(R.id.topBookRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.top_book_home_view, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book: BookHome = listBooks[position]
        Picasso.get().load(book.imageUrl).into(holder.image)
        holder.title.text = book.title
        holder.rating.rating = book.rating.toFloat()

        holder.image.setOnClickListener {
            showBook(context, book.id)
        }
    }

    override fun getItemCount(): Int {
        return listBooks.size
    }

}