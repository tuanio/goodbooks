package iuh.fivet.app_dev.goodbooks.models.toan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import iuh.fivet.app_dev.goodbooks.R

class CategoryAdapter(private var mContext: Context, private var mlistCategory: MutableList<Category>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.CategoryViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.CategoryViewHolder, position: Int) {
        val category: Category = mlistCategory[position]
        holder.tvnameCategory.text = category.nameCategory;

        val bookAdapter : BookRatedAdapter = BookRatedAdapter(category.books as ArrayList<BookRated>);
        val linearLayout : LinearLayoutManager = LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false)
        holder.rcvBook.layoutManager = linearLayout
        holder.rcvBook.adapter = bookAdapter
    }

    override fun getItemCount(): Int {
        return mlistCategory.size
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvnameCategory: TextView = itemView.findViewById(R.id.tv_name_category);
        var rcvBook: RecyclerView = itemView.findViewById(R.id.rcv_book_rated);

    }
}