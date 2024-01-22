package com.ido.ble.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
/* loaded from: classes11.dex */
public class h {

    /* renamed from: a  reason: collision with root package name */
    private static volatile long f12150a = 0;
    private static final String b = "EEE, dd MMM yyyy HH:mm:ss 'GMT'";
    private static final String c = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String d = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static synchronized String a() {
        String c2;
        synchronized (h.class) {
            c2 = c(new Date(c()));
        }
        return c2;
    }

    public static String a(Date date) {
        return b().format(date);
    }

    public static Date a(String str) {
        try {
            return d().parse(str);
        } catch (ParseException unused) {
            return b().parse(str);
        }
    }

    public static synchronized void a(long j) {
        synchronized (h.class) {
            f12150a = j - System.currentTimeMillis();
        }
    }

    public static boolean a(int i) {
        return Calendar.getInstance().get(5) == i;
    }

    public static String b(Date date) {
        return d().format(date);
    }

    private static DateFormat b() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(d, Locale.US);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return simpleDateFormat;
    }

    public static Date b(String str) {
        return e().parse(str);
    }

    public static long c() {
        return System.currentTimeMillis() + f12150a;
    }

    public static String c(Date date) {
        return e().format(date);
    }

    private static DateFormat d() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return simpleDateFormat;
    }

    private static DateFormat e() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(b, Locale.US);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return simpleDateFormat;
    }
}
