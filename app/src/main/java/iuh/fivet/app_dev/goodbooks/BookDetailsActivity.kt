package iuh.fivet.app_dev.goodbooks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import iuh.fivet.app_dev.goodbooks.fragments.MoreInfoFragment
import iuh.fivet.app_dev.goodbooks.fragments.OverviewFragment
import iuh.fivet.app_dev.goodbooks.fragments.adapters.ViewPagerAdapter

class BookDetailsActivity : AppCompatActivity() {
    private var clickChecker:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        setUpHeartButton()
        setUpTabs()

    }

    private fun setUpHeartButton() {
        val heartBtn = findViewById<Button>(R.id.heartBnt)
        heartBtn.setOnClickListener {
            clickChecker = clickChecker xor true
            if (clickChecker) {
                heartBtn.setBackgroundResource(R.drawable.heart_on)
            }
            else {
                heartBtn.setBackgroundResource(R.drawable.heart_off)
            }
        }
    }

    private fun setUpTabs() {
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val tabs = findViewById<TabLayout>(R.id.bookDetailTabLayout)
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(OverviewFragment(), "Overview")
        adapter.addFragment(MoreInfoFragment(), "More info")

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

//        Khong gian qua nho de them icon
//        tabs.getTabAt(0)!!.setIcon(R.drawable.overview)
//        tabs.getTabAt(1)!!.setIcon(R.drawable.more_info)

    }
}