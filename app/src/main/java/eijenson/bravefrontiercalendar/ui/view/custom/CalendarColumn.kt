package eijenson.bravefrontiercalendar.ui.view.custom

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import eijenson.bravefrontiercalendar.R

/**
 * Created by eijenson on 2017/08/19.
 * カレンダーの一日分のカスタムビュー
 */

class CalendarColumn(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : this(context, attrs, defStyleAttr, 0)

    init {
        init(context, attrs)
    }

    fun setText(value: String) {
        val textView = findViewById<TextView>(R.id.text_view)
        textView.text = value
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        View.inflate(context, R.layout.calendar_column, this)
        val a = context.obtainStyledAttributes(attrs, R.styleable.CalendarColumn)
        val text = a.getString(R.styleable.CalendarColumn_text)
        val textColor = a.getColor(R.styleable.CalendarColumn_textColor, ContextCompat.getColor(context, R.color.black))
        val textView = findViewById<TextView>(R.id.text_view)
        textView.text = text
        textView.setTextColor(textColor)
        a.recycle()

    }
}
