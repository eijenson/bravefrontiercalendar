package eijenson.braveflontiercarendar.view.custom

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import eijenson.braveflontiercarendar.R
import eijenson.braveflontiercarendar.repository.local.CarendarRepository

/**
 * カレンダーView
 */
class MyCalendarView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    val first = 8
    val num = 6

    init {
        init(context)
    }

    fun init(context: Context) {
        View.inflate(context, R.layout.my_calendar_view, this)
        var list = CarendarRepository().getFirstOneWeek()
        for (i in first..first + num) {
            val tvId = "textView" + i
            val resId = resources.getIdentifier(tvId, "id", context.packageName)
            val column: CalendarColumn? = findViewById(resId) as CalendarColumn?
            column?.text = list.first().date.toString()
            list = list.drop(1)
        }
    }
}