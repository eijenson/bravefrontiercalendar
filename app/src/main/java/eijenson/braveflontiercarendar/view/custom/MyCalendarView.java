package eijenson.braveflontiercarendar.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import eijenson.braveflontiercarendar.R;
import eijenson.braveflontiercarendar.repository.local.CalendarRepository;

/**
 * Created by eijenson on 2017/08/19.
 */

public class MyCalendarView extends ConstraintLayout {

    public MyCalendarView(Context context) {
        this(context, null);
    }

    public MyCalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        int first = 8;
        View.inflate(context, R.layout.my_calendar_view, this);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyCalendarView);
        String date_text = a.getString(R.styleable.MyCalendarView_date);
        if (isInEditMode()) return;
        Date selectedDate = getSelectedDate(date_text);
        List<Date> list = new CalendarRepository(selectedDate).getCalendar();
        for (Date date : list) {
            String tvId = "textView" + first;
            first++;
            int resId = getResources().getIdentifier(tvId, "id", context.getPackageName());
            CalendarColumn col = (CalendarColumn) findViewById(resId);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            col.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
            col.setVisibility(View.VISIBLE);
        }
        a.recycle();
    }

    private Date getSelectedDate(String date_text) {
        if (date_text == null) return Calendar.getInstance().getTime();
        try {
            return DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.JAPAN).parse(date_text);
        } catch (ParseException e) {
            e.printStackTrace();
            return Calendar.getInstance().getTime();
        }
    }
}