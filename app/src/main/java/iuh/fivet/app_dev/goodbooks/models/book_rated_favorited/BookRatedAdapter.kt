package iuh.fivet.app_dev.goodbooks.models.book_rated_favorited

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.internal.ContextUtils.getActivity
import com.squareup.picasso.Picasso
import iuh.fivet.app_dev.goodbooks.R
import iuh.fivet.app_dev.goodbooks.activities.BookDetailsActivity
import iuh.fivet.app_dev.goodbooks.utils.Utils.showBook
import iuh.fivet.app_dev.goodbooks.utils.Variables

class BookRatedAdapter(var context:Context ,private var mBooksRated : MutableList<BookRated>) : RecyclerView.Adapter<BookRatedAdapter.BookRatedViewHolder>() {
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
            holder.imageBookRated.setOnClickListener {
                showBook(context, bookRated.id)
                Toast.makeText(context, "id :" + bookRated.id.toString(), Toast.LENGTH_SHORT).show()
            }

    }

    override fun getItemCount(): Int {
        return mBooksRated.size
    }
}