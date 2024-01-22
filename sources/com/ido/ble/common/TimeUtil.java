package com.ido.ble.common;

import android.content.Context;
import android.text.format.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/* loaded from: classes11.dex */
public class TimeUtil {
    public static long dateToStamp(int i, int i2, int i3, int i4, int i5, int i6) {
        Date date = getDate(i, i2, i3, i4, i5, i6);
        if (date != null) {
            return date.getTime();
        }
        return 0L;
    }

    public static Date getDate() {
        return getDate(getYear(), getMonth(), getDay());
    }

    public static Date getDate(int i, int i2, int i3) {
        return getDate(i, i2, i3, 0, 0, 0);
    }

    public static Date getDate(int i, int i2, int i3, int i4, int i5, int i6) {
        return new GregorianCalendar(i, i2 - 1, i3, i4, i5, i6).getTime();
    }

    public static int getDay() {
        return GregorianCalendar.getInstance().get(5);
    }

    public static Date getEndDate(int i, int i2, int i3) {
        return getDate(i, i2, i3, 23, 59, 59);
    }

    public static long getEndDateToStamp(int i, int i2, int i3) {
        return dateToStamp(i, i2, i3, 23, 59, 59);
    }

    public static int getHour() {
        return GregorianCalendar.getInstance().get(11);
    }

    public static int getMinute() {
        return GregorianCalendar.getInstance().get(12);
    }

    public static int getMonth() {
        return GregorianCalendar.getInstance().get(2) + 1;
    }

    public static int getSecond() {
        return GregorianCalendar.getInstance().get(13);
    }

    public static Date getStartDate(int i, int i2, int i3) {
        return getDate(i, i2, i3, 0, 0, 0);
    }

    public static long getStartDateToStamp(int i, int i2, int i3) {
        return dateToStamp(i, i2, i3, 0, 0, 0);
    }

    public static int getYear() {
        return GregorianCalendar.getInstance().get(1);
    }

    public static boolean is24HourFormat(Context context) {
        return DateFormat.is24HourFormat(context);
    }

    public static boolean isSameDay(long j, long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(j));
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date(j2));
        return calendar.get(1) == calendar2.get(1) && calendar.get(2) == calendar2.get(2) && calendar.get(5) == calendar2.get(5);
    }
}
