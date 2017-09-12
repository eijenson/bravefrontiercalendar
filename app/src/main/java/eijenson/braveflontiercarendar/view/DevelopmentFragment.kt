package eijenson.braveflontiercarendar.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eijenson.braveflontiercarendar.DevUtils
import eijenson.braveflontiercarendar.R
import kotlinx.android.synthetic.main.activity_develop.*

class DevelopmentFragment : Fragment() {

    companion object {
        fun newInstance(): DevelopmentFragment {
            return DevelopmentFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clear.setOnClickListener {
            DevUtils.clear()
        }
        dev.setOnClickListener {
            DevUtils.dev()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_development, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }
}