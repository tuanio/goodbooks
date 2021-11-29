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
    private lateinit var navigationView: BottomNavigationView ;
    private val homeFragment = HomeFragment();
    private val searchFragment = SearchFragment();
    private val myfavFragment = MyFavFragment();
    private val userFragment = UserFragment();
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val repository = Repository()
//        val viewModelFacotry = MainViewModelFacotry(repository)
//        viewModel = ViewModelProvider(this, viewModelFacotry).get(MainViewModel::class.java)
//        viewModel.getBooks()
//        viewModel.myResponse.observe(this, Observer { response ->
//            val img :ImageButton = findViewById(R.id.book111)
//            val  url = response.data.list_books.get(0).image_url.toString();
//            Picasso.get().load(url).into(img);
//        });
//        replaceFragment(homeFragment)
        navigationView = findViewById(R.id.bottom_navigation)
        navigationView.setOnItemSelectedListener {
            when (it.itemId) {
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


