package iuh.fivet.app_dev.goodbooks.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import iuh.fivet.app_dev.goodbooks.models.Top100Books
import iuh.fivet.app_dev.goodbooks.databinding.BookViewBinding

class Top100BookAdapter :
    ListAdapter<Top100Books, Top100BookAdapter.top100BookViewHolder>(DiffCallback) {

    class top100BookViewHolder(
        private var binding: BookViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(top100books: Top100Books) {
            binding.top100books = top100books

            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Top100Books>() {
        override fun areItemsTheSame(oldItem: Top100Books, newItem: Top100Books): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Top100Books, newItem: Top100Books): Boolean {
            return oldItem.imgUrl == newItem.imgUrl
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): top100BookViewHolder {
        return top100BookViewHolder(
            BookViewBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: top100BookViewHolder, position: Int) {
        val top100Books = getItem(position)
        holder.bind(top100Books)
    }
}