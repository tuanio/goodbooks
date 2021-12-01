package iuh.fivet.app_dev.goodbooks.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import iuh.fivet.app_dev.goodbooks.databinding.TopGenreBookViewBinding

import iuh.fivet.app_dev.goodbooks.models.TopBooks


class TopBooksByGenreAdapter :
    ListAdapter<TopBooks, TopBooksByGenreAdapter.topBooksByGenreViewHolder>(DiffCallback) {

    class topBooksByGenreViewHolder(
        private var binding: TopGenreBookViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(topBooks: TopBooks) {
            binding.topGenreBooks = topBooks

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
    ): TopBooksByGenreAdapter.topBooksByGenreViewHolder {
        return TopBooksByGenreAdapter.topBooksByGenreViewHolder(
            TopGenreBookViewBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: TopBooksByGenreAdapter.topBooksByGenreViewHolder, position: Int) {
        val topGenreBooks = getItem(position)
        holder.bind(topGenreBooks)
    }
}