package iuh.fivet.app_dev.goodbooks.models.list_books

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import iuh.fivet.app_dev.goodbooks.R
import iuh.fivet.app_dev.goodbooks.utils.Utils.showBook

class BookAdapter(var context: Context, private val listBooks: List<Book>): RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    class BookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.bookImage)
        val title: TextView = itemView.findViewById(R.id.tvBookTitle)
        val tvAuthors: TextView = itemView.findViewById(R.id.tvAuthors)
        val tvGenres: TextView = itemView.findViewById(R.id.tvGenres)
        val mRating: RatingBar = itemView.findViewById(R.id.discoverRatingStar)
        val bookItem: LinearLayout = itemView.findViewById(R.id.bookItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book: Book = listBooks[position]
        Picasso.get().load(book.imageUrl).into(holder.image)
        holder.title.text = book.title
        holder.tvAuthors.text = concatString(book.authors)
        holder.tvGenres.text = concatString(book.genres)
        holder.mRating.rating = book.rating.toFloat()
        holder.bookItem.setOnClickListener { showBook(context, book.id) }
    }

    override fun getItemCount(): Int {
        return listBooks.size
    }

    private fun concatString(listString: List<String>): String {
        return listString.joinToString(separator = ", ")
    }
}