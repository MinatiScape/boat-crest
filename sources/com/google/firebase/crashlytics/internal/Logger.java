package com.google.firebase.crashlytics.internal;

import android.util.Log;
/* loaded from: classes10.dex */
public class Logger {
    public static final String TAG = "FirebaseCrashlytics";
    public static final Logger c = new Logger(TAG);

    /* renamed from: a  reason: collision with root package name */
    public final String f11125a;
    public int b = 4;

    public Logger(String str) {
        this.f11125a = str;
    }

    public static Logger getLogger() {
        return c;
    }

    public final boolean a(int i) {
        return this.b <= i || Log.isLoggable(this.f11125a, i);
    }

    public void d(String str, Throwable th) {
        if (a(3)) {
            Log.d(this.f11125a, str, th);
        }
    }

    public void e(String str, Throwable th) {
        if (a(6)) {
            Log.e(this.f11125a, str, th);
        }
    }

    public void i(String str, Throwable th) {
        if (a(4)) {
            Log.i(this.f11125a, str, th);
        }
    }

    public void log(int i, String str) {
        log(i, str, false);
    }

    public void v(String str, Throwable th) {
        if (a(2)) {
            Log.v(this.f11125a, str, th);
        }
    }

    public void w(String str, Throwable th) {
        if (a(5)) {
            Log.w(this.f11125a, str, th);
        }
    }

    public void log(int i, String str, boolean z) {
        if (z || a(i)) {
            Log.println(i, this.f11125a, str);
        }
    }

    public void d(String str) {
        d(str, null);
    }

    public void e(String str) {
        e(str, null);
    }

    public void i(String str) {
        i(str, null);
    }

    public void v(String str) {
        v(str, null);
    }

    public void w(String str) {
        w(str, null);
    }
}
