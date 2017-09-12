package eijenson.braveflontiercarendar.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eijenson.braveflontiercarendar.DevUtils
import eijenson.braveflontiercarendar.R
import kotlinx.android.synthetic.main.fragment_develop.*

class DevelopFragment : Fragment() {

    companion object {
        fun newInstance(): DevelopFragment {
            return DevelopFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_develop, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clear.setOnClickListener {
            DevUtils.clear()
        }
        dev.setOnClickListener {
            DevUtils.dev()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }
}