package com.android.volley;

import android.os.SystemClock;
import android.util.Log;
import androidx.camera.core.CameraInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/* loaded from: classes.dex */
public class VolleyLog {
    public static String TAG = "Volley";
    public static boolean DEBUG = Log.isLoggable("Volley", 2);

    /* renamed from: a  reason: collision with root package name */
    public static final String f2149a = VolleyLog.class.getName();

    /* loaded from: classes.dex */
    public static class a {
        public static final boolean c = VolleyLog.DEBUG;

        /* renamed from: a  reason: collision with root package name */
        public final List<C0200a> f2150a = new ArrayList();
        public boolean b = false;

        /* renamed from: com.android.volley.VolleyLog$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static class C0200a {

            /* renamed from: a  reason: collision with root package name */
            public final String f2151a;
            public final long b;
            public final long c;

            public C0200a(String str, long j, long j2) {
                this.f2151a = str;
                this.b = j;
                this.c = j2;
            }
        }

        public synchronized void a(String str, long j) {
            if (!this.b) {
                this.f2150a.add(new C0200a(str, j, SystemClock.elapsedRealtime()));
            } else {
                throw new IllegalStateException("Marker added to finished log");
            }
        }

        public synchronized void b(String str) {
            this.b = true;
            long c2 = c();
            if (c2 <= 0) {
                return;
            }
            long j = this.f2150a.get(0).c;
            VolleyLog.d("(%-4d ms) %s", Long.valueOf(c2), str);
            for (C0200a c0200a : this.f2150a) {
                long j2 = c0200a.c;
                VolleyLog.d("(+%-4d) [%2d] %s", Long.valueOf(j2 - j), Long.valueOf(c0200a.b), c0200a.f2151a);
                j = j2;
            }
        }

        public final long c() {
            if (this.f2150a.size() == 0) {
                return 0L;
            }
            long j = this.f2150a.get(0).c;
            List<C0200a> list = this.f2150a;
            return list.get(list.size() - 1).c - j;
        }

        public void finalize() throws Throwable {
            if (this.b) {
                return;
            }
            b("Request on the loose");
            VolleyLog.e("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
        }
    }

    public static String a(String str, Object... objArr) {
        String str2;
        if (objArr != null) {
            str = String.format(Locale.US, str, objArr);
        }
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        int i = 2;
        while (true) {
            if (i >= stackTrace.length) {
                str2 = CameraInfo.IMPLEMENTATION_TYPE_UNKNOWN;
                break;
            } else if (!stackTrace[i].getClassName().equals(f2149a)) {
                String className = stackTrace[i].getClassName();
                String substring = className.substring(className.lastIndexOf(46) + 1);
                str2 = substring.substring(substring.lastIndexOf(36) + 1) + "." + stackTrace[i].getMethodName();
                break;
            } else {
                i++;
            }
        }
        return String.format(Locale.US, "[%d] %s: %s", Long.valueOf(Thread.currentThread().getId()), str2, str);
    }

    public static void d(String str, Object... objArr) {
        Log.d(TAG, a(str, objArr));
    }

    public static void e(String str, Object... objArr) {
        Log.e(TAG, a(str, objArr));
    }

    public static void setTag(String str) {
        d("Changing log tag to %s", str);
        TAG = str;
        DEBUG = Log.isLoggable(str, 2);
    }

    public static void v(String str, Object... objArr) {
        if (DEBUG) {
            Log.v(TAG, a(str, objArr));
        }
    }

    public static void wtf(String str, Object... objArr) {
        Log.wtf(TAG, a(str, objArr));
    }

    public static void e(Throwable th, String str, Object... objArr) {
        Log.e(TAG, a(str, objArr), th);
    }

    public static void wtf(Throwable th, String str, Object... objArr) {
        Log.wtf(TAG, a(str, objArr), th);
    }
}
