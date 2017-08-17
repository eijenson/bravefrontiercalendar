package eijenson.braveflontiercarendar.view.custom

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import eijenson.braveflontiercarendar.R

/**
 * カレンダーView
 */
class MyCalendarView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        init(context)
    }

    fun init(context: Context) {
        View.inflate(context, R.layout.my_calendar_view, this)
    }
}