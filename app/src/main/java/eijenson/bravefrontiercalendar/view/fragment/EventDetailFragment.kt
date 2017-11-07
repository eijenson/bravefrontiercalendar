package eijenson.bravefrontiercalendar.view.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eijenson.bravefrontiercalendar.R
import kotlinx.android.synthetic.main.fragment_event_detail.*

class EventDetailFragment : Fragment() {

    companion object {
        private val TEXT = "text"
        fun newInstance(text: String): EventDetailFragment {
            val bundle = Bundle()
            bundle.putString(TEXT, text)
            val fragment = EventDetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_event_detail, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text = arguments.getString(TEXT)
        tv_text.text = text
    }
}