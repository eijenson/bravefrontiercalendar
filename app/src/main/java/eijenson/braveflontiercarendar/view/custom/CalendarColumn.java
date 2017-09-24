package eijenson.braveflontiercarendar.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import eijenson.braveflontiercarendar.R;

/**
 * Created by eijenson on 2017/08/19.
 */

public class CalendarColumn extends LinearLayout {
    public CalendarColumn(Context context) {
        this(context, null);
    }

    public CalendarColumn(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarColumn(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @SuppressWarnings("WeakerAccess")
    public CalendarColumn(Context context, AttributeSet attrs, int defStyleAttr, @SuppressWarnings("SameParameterValue") int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public void setText(String value) {
        TextView textView = findViewById(R.id.text_view);
        textView.setText(value);
    }

    private void init(Context context, AttributeSet attrs) {
        View.inflate(context, R.layout.calendar_column, this);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CalendarColumn);
        String text = a.getString(R.styleable.CalendarColumn_text);
        int textColor = a.getColor(R.styleable.CalendarColumn_textColor, context.getResources().getColor(R.color.black));
        TextView textView = findViewById(R.id.text_view);
        textView.setText(text);
        textView.setTextColor(textColor);
        a.recycle();

    }
}
