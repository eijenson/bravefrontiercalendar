package eijenson.braveflontiercarendar.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    private Calendar selectedCalendar;

    private void init(Context context, AttributeSet attrs) {
        View.inflate(context, R.layout.my_calendar_view, this);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyCalendarView);
        String date_text = a.getString(R.styleable.MyCalendarView_date);
        if (isInEditMode()) return;
        selectedCalendar = getSelectedDate(date_text);
        setCalendar(context);
        onClickPrev();
        onClickNext();
        a.recycle();
    }

    private void setCalendar(Context context) {
        int first = 8;
        setTextMonth(selectedCalendar.getTime());
        List<Date> list = new CalendarRepository(selectedCalendar.getTime()).getCalendar();
        allGone(context);
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
    }

    private void allGone(Context context) {
        int first = 8;
        int last = 49;
        for (int i = first; i <= last; i++) {
            String tvId = "textView" + first;
            first++;
            int resId = getResources().getIdentifier(tvId, "id", context.getPackageName());
            CalendarColumn col = (CalendarColumn) findViewById(resId);
            col.setVisibility(View.INVISIBLE);
        }
    }

    private void setTextMonth(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月", Locale.JAPAN);
        TextView tv = (TextView) findViewById(R.id.month);
        tv.setText(format.format(date));
    }

    private void onClickPrev() {
        Button prev = (Button) findViewById(R.id.prev);
        prev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCalendar.add(Calendar.MONTH, -1);
                setCalendar(getContext());
            }
        });
    }

    private void onClickNext() {
        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCalendar.add(Calendar.MONTH, 1);
                setCalendar(getContext());
            }
        });
    }


    private Calendar getSelectedDate(String date_text) {
        if (date_text == null) return Calendar.getInstance();
        try {
            Date date = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.JAPAN).parse(date_text);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            e.printStackTrace();
            return Calendar.getInstance();
        }
    }
}