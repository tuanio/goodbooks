package iuh.fivet.app_dev.goodbooks.fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import iuh.fivet.app_dev.goodbooks.R
import iuh.fivet.app_dev.goodbooks.models.Book

class TopBookHomeAdapter(private val listBooks: List<Book>): RecyclerView.Adapter<TopBookHomeAdapter.BookViewHolder>() {
    class BookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageButton = itemView.findViewById(R.id.topBookImgUrl)
        val title: TextView = itemView.findViewById(R.id.topBookTitle)
        val rating: RatingBar = itemView.findViewById(R.id.topBookRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.top_book_home_view, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book: Book = listBooks[position]
        Picasso.get().load(book.imageUrl).into(holder.image)
        holder.title.text = String.format("%.15s", book.title) + ".."
        holder.rating.rating = String.format("%.1f", book.rating).toFloat()
    }

    override fun getItemCount(): Int {
        return listBooks.size
    }

}