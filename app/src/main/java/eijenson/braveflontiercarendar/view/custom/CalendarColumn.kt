package eijenson.braveflontiercarendar.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import eijenson.braveflontiercarendar.R
import kotlinx.android.synthetic.main.calendar_column.view.*

/**
 * Created by kobayashimakoto on 2017/08/13.
 */
class CalendarColumn @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {
    init {
        View.inflate(context, R.layout.calendar_column, this)
        val a = context.obtainStyledAttributes(attrs, R.styleable.CalendarColumn)
        val columnText: String? = a.getString(R.styleable.CalendarColumn_text)
        text_view.text = columnText
        a.recycle()
    }

}