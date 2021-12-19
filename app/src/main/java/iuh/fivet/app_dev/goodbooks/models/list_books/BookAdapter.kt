package iuh.fivet.app_dev.goodbooks.models.list_books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import iuh.fivet.app_dev.goodbooks.R

class BookAdapter(private val listBooks: List<Book>): RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    class BookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.bookImage)
        val title: TextView = itemView.findViewById(R.id.txtBookTitle)
        val mRating: RatingBar = itemView.findViewById(R.id.ratingStar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book: Book = listBooks[position]
        Picasso.get().load(book.imageUrl).into(holder.image)
        holder.title.text = book.title
        holder.mRating.rating = book.rating.toFloat()
    }

    override fun getItemCount(): Int {
        return listBooks.size
    }
}