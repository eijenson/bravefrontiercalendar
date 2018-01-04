package eijenson.bravefrontiercalendar.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import eijenson.bravefrontiercalendar.model.BraveNews
import eijenson.bravefrontiercalendar.repository.scraping.RegexUtil
import kotlinx.android.synthetic.main.item_event.view.*


/**
 * Created by eijenson on 2017/09/21.
 * イベントリストのアダプタークラス
 */
class EventListAdapter(val context: Context, val resource: Int, private val objects: List<BraveNews>) : BaseAdapter() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view = convertView ?: layoutInflater.inflate(resource, parent, false)

        val item = getItem(position)

        view.title?.text = item.title
        view.period?.text = item.period
        view.startTime?.text = RegexUtil.formatDateTime(item.startTime)
        view.endTime?.text = RegexUtil.formatDateTime(item.endTime)
        if (item.isBetween()) {
            view?.setBackgroundColor(context.resources.getColor(android.R.color.white))
        } else {
            view?.setBackgroundColor(context.resources.getColor(android.R.color.darker_gray))
        }

        return view
    }

    override fun getItem(position: Int): BraveNews = objects[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = objects.size
}