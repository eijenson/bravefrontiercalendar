package eijenson.braveflontiercarendar.view.custom;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
        init(context);
    }

    private void init(Context context) {
        int first = 8;
        View.inflate(context, R.layout.my_calendar_view, this);
        if (isInEditMode()) return;
        List<Date> list = new CalendarRepository().getCalendar();
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
}
