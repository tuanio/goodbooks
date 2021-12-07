package iuh.fivet.app_dev.goodbooks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import iuh.fivet.app_dev.goodbooks.fragment.HomeFragment
import iuh.fivet.app_dev.goodbooks.fragment.BookCaseFragment
import iuh.fivet.app_dev.goodbooks.fragment.SearchFragment
import iuh.fivet.app_dev.goodbooks.fragment.UserFragment

class MainActivity : AppCompatActivity() {
    private lateinit var navigationView: BottomNavigationView
    private val homeFragment = HomeFragment()
    private val searchFragment = SearchFragment()
    private val bookCaseFragment = BookCaseFragment()
    private val userFragment = UserFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(homeFragment)
        navigationView = findViewById(R.id.bottom_navigation)
        navigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> replaceFragment(homeFragment)
                R.id.nav_search -> replaceFragment(searchFragment)
                R.id.nav_mybookcase -> replaceFragment(bookCaseFragment)
                R.id.nav_user -> replaceFragment(userFragment)
            }
            true
        }
    }

    private  fun replaceFragment(fragment : Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.commit()
    }
}


