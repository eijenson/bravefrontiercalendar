package eijenson.braveflontiercarendar.view.fragment

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
import eijenson.braveflontiercarendar.R
import eijenson.braveflontiercarendar.message.RxBus
import eijenson.braveflontiercarendar.presenter.EventListPresenter
import eijenson.braveflontiercarendar.repository.models.BraveNews
import eijenson.braveflontiercarendar.view.EventDetailActivity
import eijenson.braveflontiercarendar.view.adapter.EventListAdapter
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
        //TODO:データクラスにして一本化
        RxBus.listen<Int>().bindToLifecycle(this).subscribe {
            changeProgressPercent(it)
            setPersentText(progress_bar_loading.progress.toString() + "/" + progress_bar_loading.max)
        }
        RxBus.listen<String>().bindToLifecycle(this).subscribe {
            setProgressMax(it.toInt())
            setPersentText(progress_bar_loading.progress.toString() + "/" + progress_bar_loading.max)
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

    fun setPersentText(text: String) = launch(UI) {
        progress_bar_persent.text = text
    }

    fun setText(data: List<BraveNews>) {
        val adapter = EventListAdapter(context, R.layout.item_event, data)
        list_event.adapter = adapter
        list_event.setOnItemClickListener { adapterView, _, position, _ ->
            val item = adapterView.getItemAtPosition(position) as BraveNews
            showToast(item.url)
            val intent = EventDetailActivity.createIntent(context, item.detail)
            startActivity(intent)
        }
    }

    fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun getContext(): Context {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return super.getContext()
        } else {
            return activity
        }

    }

    fun showProgressBar() {
        progress_bar_loading.visibility = View.VISIBLE
        progress_bar_persent.visibility = View.VISIBLE
    }

    fun changeProgressPercent(percent: Int) {
        println(percent)
        progress_bar_loading.progress = percent
    }

    fun setProgressMax(max: Int) {
        println(max)
        progress_bar_loading.max = max
    }

    fun hideProgressBar() {
        //progress_bar_loading.visibility = View.INVISIBLE
    }
}