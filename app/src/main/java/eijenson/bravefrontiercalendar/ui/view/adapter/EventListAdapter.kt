package eijenson.bravefrontiercalendar.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import eijenson.bravefrontiercalendar.R
import eijenson.bravefrontiercalendar.repository.models.BraveNews
import eijenson.bravefrontiercalendar.repository.scraping.RegexUtil


/**
 * Created by eijenson on 2017/09/21.
 * イベントリストのアダプタークラス
 */
class EventListAdapter(val context: Context, val resource: Int, private val objects: List<BraveNews>) : BaseAdapter() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view = convertView ?: layoutInflater.inflate(resource, parent, false)
        val title = view?.findViewById<TextView>(R.id.title)
        val period = view?.findViewById<TextView>(R.id.period)
        val startTime = view?.findViewById<TextView>(R.id.startTime)
        val endTime = view?.findViewById<TextView>(R.id.endTime)

        val item = getItem(position)

        title?.text = item.title
        period?.text = item.period
        startTime?.text = RegexUtil.formatDateTime(item.startTime)
        endTime?.text = RegexUtil.formatDateTime(item.endTime)

        return view
    }

    override fun getItem(position: Int): BraveNews {
        return objects[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return objects.size
    }
}