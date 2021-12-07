package iuh.fivet.app_dev.goodbooks.models.toan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import iuh.fivet.app_dev.goodbooks.R

class BookRatedAdapter(private var mBooksRated : ArrayList<BookRated>) : RecyclerView.Adapter<BookRatedAdapter.BookRatedViewHolder>() {
    class BookRatedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageBookRated : ImageView = itemView.findViewById(R.id.img_book_rated)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookRatedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bookrated_item,parent,false)
        return BookRatedViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookRatedViewHolder, position: Int) {
            val bookRated : BookRated = mBooksRated[position]
            Picasso.get().load(bookRated.imageUrl).into(holder.imageBookRated)
    }

    override fun getItemCount(): Int {
        return mBooksRated.size
    }
}