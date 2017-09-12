package eijenson.braveflontiercarendar.view.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import eijenson.braveflontiercarendar.R
import eijenson.braveflontiercarendar.message.RxBus
import eijenson.braveflontiercarendar.presenter.EventListPresenter
import kotlinx.android.synthetic.main.fragment_event_list.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

/**
 * Created by eijenson on 2017/09/12.
 */
class EventListFragment : Fragment() {

    companion object {
        fun newInstance(): EventListFragment {
            return EventListFragment()
        }
    }

    private lateinit var presenter: EventListPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_event_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = EventListPresenter(this)
        presenter.setHtml()
    }

    override fun onResume() {
        super.onResume()
        RxBus.listen<Int>().subscribe {
            println(it)
            changeProgressPercent(it)
            setPersentText(progress_bar_loading.progress.toString() + "/" + progress_bar_loading.max)
        }
        RxBus.listen<String>().subscribe {
            println(it)
            setProgressMax(it.toInt())
            setPersentText(progress_bar_loading.progress.toString() + "/" + progress_bar_loading.max)
        }
    }

    fun setPersentText(text: String) = launch(UI) {
        progress_bar_persent.text = text
    }

    fun setText(text: String) {
        hello.text = text
    }

    fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    fun showProgressBar() {
        progress_bar_loading.visibility = View.VISIBLE
    }

    fun changeProgressPercent(percent: Int) {
        progress_bar_loading.progress = percent
    }

    fun setProgressMax(max: Int) {
        progress_bar_loading.max = max
    }

    fun hideProgressBar() {
        //progress_bar_loading.visibility = View.INVISIBLE
    }
}