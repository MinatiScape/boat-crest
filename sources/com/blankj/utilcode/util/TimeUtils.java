package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.exifinterface.media.ExifInterface;
import com.blankj.utilcode.constant.TimeConstants;
import com.clevertap.android.sdk.Constants;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes.dex */
public final class TimeUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final ThreadLocal<Map<String, SimpleDateFormat>> f2293a = new a();
    public static final String[] b = {"猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊"};
    public static final int[] c = {20, 19, 21, 21, 21, 22, 23, 23, 23, 24, 23, 22};
    public static final String[] d = {"水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"};

    /* loaded from: classes.dex */
    public static class a extends ThreadLocal<Map<String, SimpleDateFormat>> {
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public Map<String, SimpleDateFormat> initialValue() {
            return new HashMap();
        }
    }

    public TimeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static SimpleDateFormat a() {
        return getSafeDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static long b() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static String c(long j, int i) {
        if (i <= 0) {
            return null;
        }
        int min = Math.min(i, 5);
        String[] strArr = {"天", "小时", "分钟", "秒", "毫秒"};
        int i2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i2 == 0) {
            return 0 + strArr[min - 1];
        }
        StringBuilder sb = new StringBuilder();
        if (i2 < 0) {
            sb.append("-");
            j = -j;
        }
        int[] iArr = {TimeConstants.DAY, TimeConstants.HOUR, 60000, 1000, 1};
        for (int i3 = 0; i3 < min; i3++) {
            if (j >= iArr[i3]) {
                long j2 = j / iArr[i3];
                j -= iArr[i3] * j2;
                sb.append(j2);
                sb.append(strArr[i3]);
            }
        }
        return sb.toString();
    }

    public static long d(long j, int i) {
        return j / i;
    }

    public static long date2Millis(Date date) {
        return date.getTime();
    }

    public static String date2String(Date date) {
        return date2String(date, a());
    }

    public static long e(long j, int i) {
        return j * i;
    }

    public static String getChineseWeek(String str) {
        return getChineseWeek(string2Date(str, a()));
    }

    public static String getChineseZodiac(String str) {
        return getChineseZodiac(string2Date(str, a()));
    }

    public static Date getDate(long j, long j2, int i) {
        return millis2Date(j + e(j2, i));
    }

    public static Date getDateByNow(long j, int i) {
        return getDate(getNowMills(), j, i);
    }

    public static String getFitTimeSpan(String str, String str2, int i) {
        return c(string2Millis(str, a()) - string2Millis(str2, a()), i);
    }

    public static String getFitTimeSpanByNow(String str, int i) {
        return getFitTimeSpan(str, getNowString(), a(), i);
    }

    public static String getFriendlyTimeSpanByNow(String str) {
        return getFriendlyTimeSpanByNow(str, a());
    }

    public static long getMillis(long j, long j2, int i) {
        return j + e(j2, i);
    }

    public static long getMillisByNow(long j, int i) {
        return getMillis(getNowMills(), j, i);
    }

    public static Date getNowDate() {
        return new Date();
    }

    public static long getNowMills() {
        return System.currentTimeMillis();
    }

    public static String getNowString() {
        return millis2String(System.currentTimeMillis(), a());
    }

    @SuppressLint({"SimpleDateFormat"})
    public static SimpleDateFormat getSafeDateFormat(String str) {
        Map<String, SimpleDateFormat> map = f2293a.get();
        SimpleDateFormat simpleDateFormat = map.get(str);
        if (simpleDateFormat == null) {
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(str);
            map.put(str, simpleDateFormat2);
            return simpleDateFormat2;
        }
        return simpleDateFormat;
    }

    public static String getString(long j, long j2, int i) {
        return getString(j, a(), j2, i);
    }

    public static String getStringByNow(long j, int i) {
        return getStringByNow(j, a(), i);
    }

    public static long getTimeSpan(String str, String str2, int i) {
        return getTimeSpan(str, str2, a(), i);
    }

    public static long getTimeSpanByNow(String str, int i) {
        return getTimeSpan(str, getNowString(), a(), i);
    }

    public static String getUSWeek(String str) {
        return getUSWeek(string2Date(str, a()));
    }

    public static int getValueByCalendarField(int i) {
        return Calendar.getInstance().get(i);
    }

    public static String getZodiac(String str) {
        return getZodiac(string2Date(str, a()));
    }

    public static boolean isAm() {
        return Calendar.getInstance().get(9) == 0;
    }

    public static boolean isLeapYear(String str) {
        return isLeapYear(string2Date(str, a()));
    }

    public static boolean isPm() {
        return !isAm();
    }

    public static boolean isToday(String str) {
        return isToday(string2Millis(str, a()));
    }

    public static Date millis2Date(long j) {
        return new Date(j);
    }

    public static String millis2String(long j) {
        return millis2String(j, a());
    }

    public static Date string2Date(String str) {
        return string2Date(str, a());
    }

    public static long string2Millis(String str) {
        return string2Millis(str, a());
    }

    public static String date2String(Date date, @NonNull String str) {
        Objects.requireNonNull(str, "Argument 'pattern' of type String (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getSafeDateFormat(str).format(date);
    }

    public static String getChineseWeek(String str, @NonNull DateFormat dateFormat) {
        Objects.requireNonNull(dateFormat, "Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getChineseWeek(string2Date(str, dateFormat));
    }

    public static String getChineseZodiac(String str, @NonNull DateFormat dateFormat) {
        Objects.requireNonNull(dateFormat, "Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getChineseZodiac(string2Date(str, dateFormat));
    }

    public static Date getDate(String str, long j, int i) {
        return getDate(str, a(), j, i);
    }

    public static String getFitTimeSpanByNow(String str, @NonNull DateFormat dateFormat, int i) {
        Objects.requireNonNull(dateFormat, "Argument 'format' of type DateFormat (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getFitTimeSpan(str, getNowString(dateFormat), dateFormat, i);
    }

    public static String getFriendlyTimeSpanByNow(String str, @NonNull DateFormat dateFormat) {
        Objects.requireNonNull(dateFormat, "Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getFriendlyTimeSpanByNow(string2Millis(str, dateFormat));
    }

    public static long getMillis(String str, long j, int i) {
        return getMillis(str, a(), j, i);
    }

    public static String getNowString(@NonNull DateFormat dateFormat) {
        Objects.requireNonNull(dateFormat, "Argument 'format' of type DateFormat (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return millis2String(System.currentTimeMillis(), dateFormat);
    }

    public static String getString(long j, @NonNull DateFormat dateFormat, long j2, int i) {
        Objects.requireNonNull(dateFormat, "Argument 'format' of type DateFormat (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return millis2String(j + e(j2, i), dateFormat);
    }

    public static String getStringByNow(long j, @NonNull DateFormat dateFormat, int i) {
        Objects.requireNonNull(dateFormat, "Argument 'format' of type DateFormat (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getString(getNowMills(), dateFormat, j, i);
    }

    public static long getTimeSpan(String str, String str2, @NonNull DateFormat dateFormat, int i) {
        Objects.requireNonNull(dateFormat, "Argument 'format' of type DateFormat (#2 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return d(string2Millis(str, dateFormat) - string2Millis(str2, dateFormat), i);
    }

    public static long getTimeSpanByNow(String str, @NonNull DateFormat dateFormat, int i) {
        Objects.requireNonNull(dateFormat, "Argument 'format' of type DateFormat (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getTimeSpan(str, getNowString(dateFormat), dateFormat, i);
    }

    public static String getUSWeek(String str, @NonNull DateFormat dateFormat) {
        Objects.requireNonNull(dateFormat, "Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getUSWeek(string2Date(str, dateFormat));
    }

    public static String getZodiac(String str, @NonNull DateFormat dateFormat) {
        Objects.requireNonNull(dateFormat, "Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getZodiac(string2Date(str, dateFormat));
    }

    public static boolean isLeapYear(String str, @NonNull DateFormat dateFormat) {
        Objects.requireNonNull(dateFormat, "Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return isLeapYear(string2Date(str, dateFormat));
    }

    public static boolean isPm(String str) {
        return !isAm(str);
    }

    public static boolean isToday(String str, @NonNull DateFormat dateFormat) {
        Objects.requireNonNull(dateFormat, "Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return isToday(string2Millis(str, dateFormat));
    }

    public static String millis2String(long j, @NonNull String str) {
        Objects.requireNonNull(str, "Argument 'pattern' of type String (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return millis2String(j, getSafeDateFormat(str));
    }

    public static Date string2Date(String str, @NonNull String str2) {
        Objects.requireNonNull(str2, "Argument 'pattern' of type String (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return string2Date(str, getSafeDateFormat(str2));
    }

    public static long string2Millis(String str, @NonNull String str2) {
        Objects.requireNonNull(str2, "Argument 'pattern' of type String (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return string2Millis(str, getSafeDateFormat(str2));
    }

    public static Date getDate(String str, @NonNull DateFormat dateFormat, long j, int i) {
        Objects.requireNonNull(dateFormat, "Argument 'format' of type DateFormat (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return millis2Date(string2Millis(str, dateFormat) + e(j, i));
    }

    public static String getFitTimeSpan(String str, String str2, @NonNull DateFormat dateFormat, int i) {
        Objects.requireNonNull(dateFormat, "Argument 'format' of type DateFormat (#2 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return c(string2Millis(str, dateFormat) - string2Millis(str2, dateFormat), i);
    }

    public static long getMillis(String str, @NonNull DateFormat dateFormat, long j, int i) {
        Objects.requireNonNull(dateFormat, "Argument 'format' of type DateFormat (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return string2Millis(str, dateFormat) + e(j, i);
    }

    public static int getValueByCalendarField(String str, int i) {
        return getValueByCalendarField(string2Date(str, a()), i);
    }

    public static boolean isAm(String str) {
        return getValueByCalendarField(str, a(), 9) == 0;
    }

    public static boolean isPm(String str, @NonNull DateFormat dateFormat) {
        Objects.requireNonNull(dateFormat, "Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return !isAm(str, dateFormat);
    }

    public static String date2String(Date date, @NonNull DateFormat dateFormat) {
        Objects.requireNonNull(dateFormat, "Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return dateFormat.format(date);
    }

    public static String getChineseWeek(Date date) {
        return new SimpleDateFormat(ExifInterface.LONGITUDE_EAST, Locale.CHINA).format(date);
    }

    public static String getChineseZodiac(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return b[calendar.get(1) % 12];
    }

    public static String getFitTimeSpanByNow(Date date, int i) {
        return getFitTimeSpan(date, getNowDate(), i);
    }

    public static String getFriendlyTimeSpanByNow(Date date) {
        return getFriendlyTimeSpanByNow(date.getTime());
    }

    public static String getString(String str, long j, int i) {
        return getString(str, a(), j, i);
    }

    public static long getTimeSpan(Date date, Date date2, int i) {
        return d(date2Millis(date) - date2Millis(date2), i);
    }

    public static long getTimeSpanByNow(Date date, int i) {
        return getTimeSpan(date, new Date(), i);
    }

    public static String getUSWeek(Date date) {
        return new SimpleDateFormat("EEEE", Locale.US).format(date);
    }

    public static int getValueByCalendarField(String str, @NonNull DateFormat dateFormat, int i) {
        Objects.requireNonNull(dateFormat, "Argument 'format' of type DateFormat (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getValueByCalendarField(string2Date(str, dateFormat), i);
    }

    public static String getZodiac(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getZodiac(calendar.get(2) + 1, calendar.get(5));
    }

    public static boolean isAm(String str, @NonNull DateFormat dateFormat) {
        Objects.requireNonNull(dateFormat, "Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getValueByCalendarField(str, dateFormat, 9) == 0;
    }

    public static boolean isLeapYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return isLeapYear(calendar.get(1));
    }

    public static boolean isToday(Date date) {
        return isToday(date.getTime());
    }

    public static String millis2String(long j, @NonNull DateFormat dateFormat) {
        Objects.requireNonNull(dateFormat, "Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return dateFormat.format(new Date(j));
    }

    public static Date string2Date(String str, @NonNull DateFormat dateFormat) {
        Objects.requireNonNull(dateFormat, "Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long string2Millis(String str, @NonNull DateFormat dateFormat) {
        Objects.requireNonNull(dateFormat, "Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        try {
            return dateFormat.parse(str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    public static String getChineseWeek(long j) {
        return getChineseWeek(new Date(j));
    }

    public static Date getDate(Date date, long j, int i) {
        return millis2Date(date2Millis(date) + e(j, i));
    }

    public static String getFitTimeSpanByNow(long j, int i) {
        return getFitTimeSpan(j, System.currentTimeMillis(), i);
    }

    public static String getFriendlyTimeSpanByNow(long j) {
        long currentTimeMillis = System.currentTimeMillis() - j;
        if (currentTimeMillis < 0) {
            return String.format("%tc", Long.valueOf(j));
        }
        if (currentTimeMillis < 1000) {
            return "刚刚";
        }
        if (currentTimeMillis < Constants.ONE_MIN_IN_MILLIS) {
            return String.format(Locale.getDefault(), "%d秒前", Long.valueOf(currentTimeMillis / 1000));
        }
        if (currentTimeMillis < 3600000) {
            return String.format(Locale.getDefault(), "%d分钟前", Long.valueOf(currentTimeMillis / Constants.ONE_MIN_IN_MILLIS));
        }
        long b2 = b();
        return j >= b2 ? String.format("今天%tR", Long.valueOf(j)) : j >= b2 - 86400000 ? String.format("昨天%tR", Long.valueOf(j)) : String.format("%tF", Long.valueOf(j));
    }

    public static long getMillis(Date date, long j, int i) {
        return date2Millis(date) + e(j, i);
    }

    public static String getString(String str, @NonNull DateFormat dateFormat, long j, int i) {
        Objects.requireNonNull(dateFormat, "Argument 'format' of type DateFormat (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return millis2String(string2Millis(str, dateFormat) + e(j, i), dateFormat);
    }

    public static long getTimeSpan(long j, long j2, int i) {
        return d(j - j2, i);
    }

    public static long getTimeSpanByNow(long j, int i) {
        return getTimeSpan(j, System.currentTimeMillis(), i);
    }

    public static String getUSWeek(long j) {
        return getUSWeek(new Date(j));
    }

    public static boolean isPm(Date date) {
        return !isAm(date);
    }

    public static boolean isToday(long j) {
        long b2 = b();
        return j >= b2 && j < b2 + 86400000;
    }

    public static String getFitTimeSpan(Date date, Date date2, int i) {
        return c(date2Millis(date) - date2Millis(date2), i);
    }

    public static int getValueByCalendarField(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(i);
    }

    public static boolean isAm(Date date) {
        return getValueByCalendarField(date, 9) == 0;
    }

    public static boolean isPm(long j) {
        return !isAm(j);
    }

    public static String getChineseZodiac(long j) {
        return getChineseZodiac(millis2Date(j));
    }

    public static String getFitTimeSpan(long j, long j2, int i) {
        return c(j - j2, i);
    }

    public static String getString(Date date, long j, int i) {
        return getString(date, a(), j, i);
    }

    public static boolean isAm(long j) {
        return getValueByCalendarField(j, 9) == 0;
    }

    public static String getChineseZodiac(int i) {
        return b[i % 12];
    }

    public static String getString(Date date, @NonNull DateFormat dateFormat, long j, int i) {
        Objects.requireNonNull(dateFormat, "Argument 'format' of type DateFormat (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return millis2String(date2Millis(date) + e(j, i), dateFormat);
    }

    public static boolean isLeapYear(long j) {
        return isLeapYear(millis2Date(j));
    }

    public static int getValueByCalendarField(long j, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return calendar.get(i);
    }

    public static String getZodiac(long j) {
        return getZodiac(millis2Date(j));
    }

    public static boolean isLeapYear(int i) {
        return (i % 4 == 0 && i % 100 != 0) || i % 400 == 0;
    }

    public static String getZodiac(int i, int i2) {
        String[] strArr = d;
        int i3 = i - 1;
        if (i2 < c[i3]) {
            i3 = (i + 10) % 12;
        }
        return strArr[i3];
    }
}
