package iuh.fivet.app_dev.goodbooks.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import iuh.fivet.app_dev.goodbooks.models.TopBooks
import iuh.fivet.app_dev.goodbooks.databinding.Top100BookViewBinding

class Top100BookAdapter :
    ListAdapter<TopBooks, Top100BookAdapter.top100BookViewHolder>(DiffCallback) {

    class top100BookViewHolder(
        private var binding: Top100BookViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(topBooks: TopBooks) {
            binding.top100books = topBooks

            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<TopBooks>() {
        override fun areItemsTheSame(oldItem: TopBooks, newItem: TopBooks): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TopBooks, newItem: TopBooks): Boolean {
            return oldItem.imgUrl == newItem.imgUrl
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): top100BookViewHolder {
        return top100BookViewHolder(
            Top100BookViewBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: top100BookViewHolder, position: Int) {
        val top100Books = getItem(position)
        holder.bind(top100Books)
    }
}