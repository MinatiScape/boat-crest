package com.google.firebase.installations.remote;

import androidx.annotation.GuardedBy;
import com.google.firebase.installations.Utils;
import java.util.concurrent.TimeUnit;
/* loaded from: classes10.dex */
public class c {
    public static final long d = TimeUnit.HOURS.toMillis(24);
    public static final long e = TimeUnit.MINUTES.toMillis(30);

    /* renamed from: a  reason: collision with root package name */
    public final Utils f11324a = Utils.getInstance();
    @GuardedBy("this")
    public long b;
    @GuardedBy("this")
    public int c;

    public static boolean c(int i) {
        return i == 429 || (i >= 500 && i < 600);
    }

    public static boolean d(int i) {
        return (i >= 200 && i < 300) || i == 401 || i == 404;
    }

    public final synchronized long a(int i) {
        if (!c(i)) {
            return d;
        }
        return (long) Math.min(Math.pow(2.0d, this.c) + this.f11324a.getRandomDelayForSyncPrevention(), e);
    }

    public synchronized boolean b() {
        boolean z;
        if (this.c != 0) {
            z = this.f11324a.currentTimeInMillis() > this.b;
        }
        return z;
    }

    public final synchronized void e() {
        this.c = 0;
    }

    public synchronized void f(int i) {
        if (d(i)) {
            e();
            return;
        }
        this.c++;
        this.b = this.f11324a.currentTimeInMillis() + a(i);
    }
}
