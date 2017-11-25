package eijenson.bravefrontiercalendar.ui.view.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eijenson.bravefrontiercalendar.R

/**
 * Created by eijenson on 2017/09/12.
 * 初期表示画面のフラグメント
 */
class MainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = inflater.inflate(R.layout.fragment_main, container, false)
}