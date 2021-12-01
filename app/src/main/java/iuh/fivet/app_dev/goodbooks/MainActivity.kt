package iuh.fivet.app_dev.goodbooks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import iuh.fivet.app_dev.goodbooks.fragment.HomeFragment
import iuh.fivet.app_dev.goodbooks.fragment.MyFavFragment
import iuh.fivet.app_dev.goodbooks.fragment.SearchFragment
import iuh.fivet.app_dev.goodbooks.fragment.UserFragment
import iuh.fivet.app_dev.goodbooks.repository.MainViewModel
import iuh.fivet.app_dev.goodbooks.repository.Repository

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val parent_button = findViewById<Button>(R.id.parent_button)
//
//        parent_button.setOnClickListener {view ->
//            view.findNavController().navigate(R.layout.activity_book_details)
//        }

//        setUpTabs()

    }


//    private fun setUpTabs() {
//        val viewPager = findViewById<ViewPager>(R.id.viewPager)
//        val tabs = findViewById<TabLayout>(R.id.tabs)
//        val adapter = ViewPagerAdapter(supportFragmentManager)
//        adapter.addFragment(HomeFragment(), "Home")
//        adapter.addFragment(FavouritesFragment(), "Favourites")
//
//        viewPager.adapter = adapter
//        tabs.setupWithViewPager(viewPager)
//
//        tabs.getTabAt(0)!!.setIcon(R.drawable.info)
//        tabs.getTabAt(1)!!.setIcon(R.drawable.overview)
//
//    }

}
