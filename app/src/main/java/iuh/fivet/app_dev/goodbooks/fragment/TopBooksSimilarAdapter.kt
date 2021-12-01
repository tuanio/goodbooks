package iuh.fivet.app_dev.goodbooks.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import iuh.fivet.app_dev.goodbooks.databinding.TopAuthorBookViewBinding
import iuh.fivet.app_dev.goodbooks.models.TopBooks

class TopBooksSimilarAdapter :
    ListAdapter<TopBooks, TopBooksSimilarAdapter.topBooksSimilarViewHolder>(DiffCallback) {

    class topBooksSimilarViewHolder(
        private var binding: TopAuthorBookViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(topBooks: TopBooks) {
            binding.topAuthorBooks = topBooks

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
    ): TopBooksSimilarAdapter.topBooksSimilarViewHolder {
        return TopBooksSimilarAdapter.topBooksSimilarViewHolder(
            TopAuthorBookViewBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: TopBooksSimilarAdapter.topBooksSimilarViewHolder, position: Int) {
        val topAuthorBooks = getItem(position)
        holder.bind(topAuthorBooks)
    }
}