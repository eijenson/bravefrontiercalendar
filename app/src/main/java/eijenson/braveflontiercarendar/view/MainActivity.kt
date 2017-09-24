package eijenson.braveflontiercarendar.view

import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import eijenson.braveflontiercarendar.R
import eijenson.braveflontiercarendar.view.fragment.CalendarFragment
import eijenson.braveflontiercarendar.view.fragment.DevelopFragment
import eijenson.braveflontiercarendar.view.fragment.EventListFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * メインアクティビティ
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            if (bottom_navigation.selectedItemId == item.itemId) {
                return@setOnNavigationItemSelectedListener true
            }
            when (item.itemId) {
                R.id.calendar -> {
                    moveToFragment(CalendarFragment.newInstance())
                }
                R.id.list -> {
                    moveToFragment(EventListFragment.newInstance())
                }
                R.id.dev -> {
                    moveToFragment(DevelopFragment.newInstance())
                }
                else -> {
                    println("xxx")
                }
            }
            true
        }
    }

    fun initFragment() {
        val fragment = CalendarFragment.newInstance()
        fragmentManager.beginTransaction()
                .add(R.id.main_content, fragment)
                .commit()
    }

    fun moveToFragment(fragment: Fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.main_content, fragment)
                .commit()
    }

}
