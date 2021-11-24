package iuh.fivet.app_dev.goodbooks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import iuh.fivet.app_dev.goodbooks.R.id.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url : String = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1344409006l/142296.jpg"
        val ap : ImageButton = findViewById(R.id.book10);

        val url2: String = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1408500437l/7094569.jpg"
        val ap2 :ImageButton = findViewById(R.id.book9)
        Picasso.get().load(url).into(ap)
        Picasso.get().load(url2).into(ap2)
    }

}

