package iuh.fivet.app_dev.goodbooks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import iuh.fivet.app_dev.goodbooks.R.id.*

class MainActivity : AppCompatActivity() {
    private lateinit var navigationView: BottomNavigationView ;
    private val homeFragment = HomeFragment();
    private val searchFragment = SearchFragment();
    private val myfavFragment = MyFavFragment();
    private val userFragment = UserFragment();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val url : String = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1344409006l/142296.jpg"
//        val ap : ImageButton = findViewById(R.id.book10);
//
//        val url2: String = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1408500437l/7094569.jpg"
//        val ap2 :ImageButton = findViewById(R.id.book9);
//        Picasso.get().load(url).into(ap)
//        Picasso.get().load(url2).into(ap2)

        replaceFragment(homeFragment)
        navigationView = findViewById(R.id.bottom_navigation)
        navigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> replaceFragment(homeFragment)
                R.id.nav_search -> replaceFragment(searchFragment)
                R.id.nav_myfav -> replaceFragment(myfavFragment)
                R.id.nav_user -> replaceFragment(userFragment)
            }
            true
        }

        }
        private  fun replaceFragment (fragment : Fragment){
            if(fragment != null){
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container,fragment)
                transaction.commit()
            }
        }
    }


