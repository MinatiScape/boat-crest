package com.abupdate.iot_libs.utils;

import android.os.Handler;
import android.os.Looper;
import com.abupdate.iot_libs.inter.TimeoutCallback;
/* loaded from: classes.dex */
public class i {
    public static i d;
    public TimeoutCallback b;
    public Runnable c = new a();

    /* renamed from: a  reason: collision with root package name */
    public Handler f1919a = new Handler(Looper.getMainLooper());

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            i.this.b.onTimeout();
        }
    }

    public void b() {
        this.f1919a.removeCallbacks(this.c);
    }

    public static i a() {
        if (d == null) {
            d = new i();
        }
        return d;
    }

    public void a(TimeoutCallback timeoutCallback) {
        this.b = timeoutCallback;
        b();
        this.f1919a.postDelayed(this.c, 5000L);
    }
}
