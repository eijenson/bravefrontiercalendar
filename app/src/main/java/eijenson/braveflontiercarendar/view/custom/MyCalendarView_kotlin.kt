package eijenson.braveflontiercarendar.view.custom

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import eijenson.braveflontiercarendar.R
import eijenson.braveflontiercarendar.repository.local.CalendarRepository

/**
 * カレンダーView
 */
class MyCalendarView_kotlin @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    val first = 8
    val num = 7 * 5 - 1

    init {
        init(context)
    }

    fun init(context: Context) {
        View.inflate(context, R.layout.my_calendar_view, this)
        var list = CalendarRepository().getCalendar()
        for (i in first..first + num) {
            val tvId = "textView" + i
            val resId = resources.getIdentifier(tvId, "id", context.packageName)
            val columnKotlin: CalendarColumn_kotlin? = findViewById(resId) as CalendarColumn_kotlin?
            columnKotlin?.text = list.first().date.toString()
            list = list.drop(1)
        }
    }
}