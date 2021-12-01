package iuh.fivet.app_dev.goodbooks

import android.view.View
import android.widget.ImageButton
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import iuh.fivet.app_dev.goodbooks.fragment.ApiStatus
import iuh.fivet.app_dev.goodbooks.fragment.Top100BookAdapter
import iuh.fivet.app_dev.goodbooks.models.Top100Books

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Top100Books>?) {
    val adapter = recyclerView.adapter as Top100BookAdapter
    adapter.submitList(data)
}

@BindingAdapter("imgUrl")
fun bindImage(imgView: ImageButton, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("title")
fun bindTitle(textView: TextView, title: String?) {
    textView.text = String.format("%.15s", title) + ".."
}

@BindingAdapter("rating")
fun bindRating(ratingBar: RatingBar, weighted_rating: Float?) {
    ratingBar.numStars = String.format("%.1f", weighted_rating).toInt()
}


@BindingAdapter("ApiStatus")
fun bindStatus(statusImageView: ImageButton, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        null -> TODO()
    }
}
