package eijenson.bravefrontiercalendar.ui.view.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import eijenson.bravefrontiercalendar.R
import kotlinx.android.synthetic.main.fragment_web_view.*


/**
 * Created by kobayashimakoto on 2017/11/07.
 * WebViewでWebページを表示するFragment
 */
class WebViewFragment : Fragment() {
    companion object {
        private val URL = "url"
        fun newInstance(url: String): WebViewFragment {
            val bundle = Bundle()
            bundle.putString(URL, url)
            val fragment = WebViewFragment()
            fragment.arguments = bundle
            return fragment

        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View = inflater!!.inflate(R.layout.fragment_web_view, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        web_view.webViewClient = WebViewClient()
        web_view.loadUrl(arguments.getString(URL))
    }
}