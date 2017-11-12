package eijenson.bravefrontiercalendar.ui.view.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eijenson.bravefrontiercalendar.R

/**
 * Created by eijenson on 2017/09/12.
 * カレンダーを表示する画面のフラグメント
 */
class CalendarFragment : Fragment() {

    companion object {
        fun newInstance(): CalendarFragment {
            return CalendarFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }
}