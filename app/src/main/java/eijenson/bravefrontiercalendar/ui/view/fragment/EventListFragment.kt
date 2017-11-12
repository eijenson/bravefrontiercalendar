package eijenson.bravefrontiercalendar.ui.view.fragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.trello.rxlifecycle2.components.RxFragment
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import eijenson.bravefrontiercalendar.R
import eijenson.bravefrontiercalendar.message.RxBus
import eijenson.bravefrontiercalendar.presenter.EventListPresenter
import eijenson.bravefrontiercalendar.repository.models.BraveNews
import eijenson.bravefrontiercalendar.repository.models.Progress
import eijenson.bravefrontiercalendar.ui.view.EventDetailActivity
import eijenson.bravefrontiercalendar.ui.view.adapter.EventListAdapter
import kotlinx.android.synthetic.main.fragment_event_list.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch


/**
 * Created by eijenson on 2017/09/12.
 * イベントリストを表示するフラグメント
 */
class EventListFragment : RxFragment() {

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
        //TODO:リストの下が見えない
        presenter.setHtml()
        Log.d("EventListFragment", "onViewCreated")
    }

    override fun onResume() {
        super.onResume()
        RxBus.listen<Progress>().bindToLifecycle(this).subscribe {
            setProgressPercent(it.now)
            setProgressMax(it.max)
            setPercentText(it.now.toString() + "/" + it.max.toString())
        }
        RxBus.listen<List<BraveNews>>().bindToLifecycle(this).subscribe {
            setText(it)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
        Log.d("EventListFragment", "onDestroy")
    }

    private fun setPercentText(text: String) = launch(UI) {
        progress_bar_percent.text = text
    }

    private fun setText(data: List<BraveNews>) {
        val adapter = EventListAdapter(context, R.layout.item_event, data)
        list_event.adapter = adapter
        list_event.setOnItemClickListener { adapterView, _, position, _ ->
            val item = adapterView.getItemAtPosition(position) as BraveNews
            showToast(item.url)
            val intent = EventDetailActivity.createIntent(context, item.url)
            activity.startActivity(intent)
        }
    }

    fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun getContext(): Context {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            super.getContext()
        } else {
            activity
        }

    }

    fun showProgressBar() {
        progress_bar_loading.visibility = View.VISIBLE
        progress_bar_percent.visibility = View.VISIBLE
    }

    private fun setProgressPercent(percent: Int) {
        progress_bar_loading.progress = percent
    }

    private fun setProgressMax(max: Int) {
        progress_bar_loading.max = max
    }

    fun hideProgressBar() {
        progress_bar_loading.visibility = View.GONE
        progress_bar_percent.visibility = View.GONE
    }
}