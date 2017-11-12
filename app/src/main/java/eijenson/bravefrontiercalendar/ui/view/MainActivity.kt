package eijenson.bravefrontiercalendar.ui.view

import android.app.Fragment
import android.os.Bundle
import com.trello.rxlifecycle2.components.RxActivity
import eijenson.bravefrontiercalendar.R
import eijenson.bravefrontiercalendar.ui.view.fragment.CalendarFragment
import eijenson.bravefrontiercalendar.ui.view.fragment.DevelopFragment
import eijenson.bravefrontiercalendar.ui.view.fragment.EventListFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * メインアクティビティ
 */
class MainActivity : RxActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
        setActionBar(tool_bar)
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

    private fun initFragment() {
        val fragment = CalendarFragment.newInstance()
        fragmentManager.beginTransaction()
                .add(R.id.main_content, fragment)
                .commit()
    }

    private fun moveToFragment(fragment: Fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.main_content, fragment)
                .commit()
    }

}
