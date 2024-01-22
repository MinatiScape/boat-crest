package com.crrepa.i0;

import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/* loaded from: classes9.dex */
public class h {
    public static int a() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(11);
    }

    public static int a(int i, int i2) {
        return (i * 60) + i2;
    }

    public static String a(String str) {
        Date date = new Date(System.currentTimeMillis());
        return TextUtils.isEmpty(str) ? date.toString() : a(date, str);
    }

    public static String a(Date date, String str) {
        if (date == null && TextUtils.isEmpty(str)) {
            return null;
        }
        return new SimpleDateFormat(str).format(date);
    }
}
