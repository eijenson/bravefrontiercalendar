package eijenson.braveflontiercarendar.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import eijenson.braveflontiercarendar.R
import eijenson.braveflontiercarendar.repository.models.BraveNews
import eijenson.braveflontiercarendar.repository.scraping.RegexUtil


/**
 * Created by eijenson on 2017/09/21.
 */
class EventListAdapter(val context: Context, val resource: Int, val objects: List<BraveNews>) : BaseAdapter() {
    val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        if (convertView == null) {
            view = layoutInflater.inflate(resource, parent, false)
        } else {
            view = convertView
        }
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
        return objects.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return objects.size
    }
}