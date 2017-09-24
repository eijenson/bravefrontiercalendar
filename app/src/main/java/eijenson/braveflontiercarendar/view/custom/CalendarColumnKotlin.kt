package eijenson.braveflontiercarendar.view.custom

import android.content.Context
import android.os.Build
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import eijenson.braveflontiercarendar.R
import kotlinx.android.synthetic.main.calendar_column.view.*

/**
 * Created by kobayashimakoto on 2017/08/13.
 */
class CalendarColumnKotlin @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
        defStyleRes: Int = 0) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    var text: String
        get() = text_view.text.toString()
        set(value) {
            text_view.text = value
        }

    init {
        init(context, attrs)
    }

    fun init(context: Context, attrs: AttributeSet?) {
        View.inflate(context, R.layout.calendar_column, this)
        val a = context.obtainStyledAttributes(attrs, R.styleable.CalendarColumn)
        val text: String? = a.getString(R.styleable.CalendarColumn_text)
        val textColor = a.getColor(R.styleable.CalendarColumn_textColor, getColor(context, R.color.black))
        text_view.text = text
        text_view.setTextColor(textColor)
        a.recycle()
    }

    @Suppress("DEPRECATION")
    @ColorInt
    private fun getColor(context: Context, @ColorRes id: Int): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(id)
        } else {
            return context.resources.getColor(id)
        }
    }
}