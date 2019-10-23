package com.test.privat.currency.models.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static String getTodayStringDate() {
        return getOffsetDayString(0);
    }

    public static String getYesterdayStringDate() {
        return getOffsetDayString(-1);
    }

    public static String getOffsetDayString(int daysOffset) {
        Date date = new Date();

        Calendar instance = Calendar.getInstance();

        instance.setTime(date);
        instance.set(Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH + daysOffset);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        return dateFormat.format(Date.from(instance.toInstant()));
    }
}
