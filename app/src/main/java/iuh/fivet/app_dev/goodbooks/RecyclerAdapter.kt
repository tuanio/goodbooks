package iuh.fivet.app_dev.goodbooks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import iuh.fivet.app_dev.goodbooks.models.Book
import java.math.BigDecimal
import java.math.RoundingMode

class RecyclerAdapter(private val listBooks: List<Book>): RecyclerView.Adapter<RecyclerAdapter.BookViewHolder>() {
    class BookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.bookImage)
        val title: TextView = itemView.findViewById(R.id.txtBookTitle)
        val rating: TextView = itemView.findViewById(R.id.txtRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book: Book = listBooks[position]
        Picasso.get().load(book.imageUrl).into(holder.image)
        holder.title.text = book.title
        holder.rating.text = BigDecimal(book.rating).setScale(2, RoundingMode.CEILING).toString()
    }

    override fun getItemCount(): Int {
        return listBooks.size
    }
}