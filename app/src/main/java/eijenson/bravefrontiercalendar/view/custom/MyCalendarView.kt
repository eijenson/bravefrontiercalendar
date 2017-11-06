package eijenson.bravefrontiercalendar.view.custom

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.TextView
import eijenson.bravefrontiercalendar.R
import eijenson.bravefrontiercalendar.repository.local.CalendarRepository
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by eijenson on 2017/08/19.
 * カレンダー用View
 */

class MyCalendarView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var selectedCalendar: Calendar? = null
    private var dateText: String? = null

    private val month: String
        get() = selectedCalendar!!.get(Calendar.YEAR).toString() + "/" + (selectedCalendar!!.get(Calendar.MONTH) + 1) + "/" + selectedCalendar!!.get(Calendar.DATE)

    init {
        init(context, attrs)
    }

    override fun onSaveInstanceState(): Parcelable? {
        val parcelable = super.onSaveInstanceState()
        val b = Bundle()
        b.putParcelable("parent", parcelable)
        b.putString("date", month)
        return b
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        val b = state as Bundle
        super.onRestoreInstanceState(b.getParcelable("parent"))
        dateText = b.getString("date")
        selectedCalendar = getSelectedDate(dateText)
        setCalendar(this.context)

    }

    private fun init(context: Context, attrs: AttributeSet?) {
        View.inflate(context, R.layout.my_calendar_view, this)
        val a = context.obtainStyledAttributes(attrs, R.styleable.MyCalendarView)
        if (dateText == null) {
            dateText = a.getString(R.styleable.MyCalendarView_date)
        }
        if (isInEditMode) return
        selectedCalendar = getSelectedDate(dateText)
        setCalendar(context)
        onClickPrev()
        onClickNext()
        a.recycle()
    }

    private fun setCalendar(context: Context) {
        var first = 1
        setTextMonth(selectedCalendar!!.time)
        val list = CalendarRepository(selectedCalendar!!.time).getCalendar()
        allGone(context)
        for (date in list) {
            val tvId = "textView" + first
            first++
            val resId = resources.getIdentifier(tvId, "id", context.packageName)
            val col = findViewById<CalendarColumn>(resId)
            val cal = Calendar.getInstance()
            cal.time = date
            col.setText(cal.get(Calendar.DAY_OF_MONTH).toString())
            col.visibility = View.VISIBLE
        }
    }

    private fun allGone(context: Context) {
        var first = 31
        val last = 42
        for (i in first..last) {
            val tvId = "textView" + first
            first++
            val resId = resources.getIdentifier(tvId, "id", context.packageName)
            val col = findViewById<CalendarColumn>(resId)
            col.visibility = View.INVISIBLE
        }
    }

    private fun setTextMonth(date: Date) {
        val format = SimpleDateFormat("yyyy年MM月", Locale.JAPAN)
        val tv = findViewById<TextView>(R.id.month)
        tv.text = format.format(date)
    }

    private fun onClickPrev() {
        val prev = findViewById<Button>(R.id.prev)
        prev.setOnClickListener {
            selectedCalendar!!.add(Calendar.MONTH, -1)
            setCalendar(context)
        }
    }

    private fun onClickNext() {
        val next = findViewById<Button>(R.id.next)
        next.setOnClickListener {
            selectedCalendar!!.add(Calendar.MONTH, 1)
            setCalendar(context)
        }
    }

    private fun getSelectedDate(date_text: String?): Calendar {
        if (date_text == null) return Calendar.getInstance()
        try {
            val date = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.JAPAN).parse(date_text)
            val calendar = Calendar.getInstance()
            calendar.time = date
            return calendar
        } catch (e: ParseException) {
            e.printStackTrace()
            return Calendar.getInstance()
        }

    }
}