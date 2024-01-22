package com.google.android.gms.measurement.internal;

import android.os.Handler;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes10.dex */
public abstract class f {
    public static volatile Handler d;

    /* renamed from: a  reason: collision with root package name */
    public final y0 f10114a;
    public final Runnable b;
    public volatile long c;

    public f(y0 y0Var) {
        Preconditions.checkNotNull(y0Var);
        this.f10114a = y0Var;
        this.b = new e(this, y0Var);
    }

    public final void b() {
        this.c = 0L;
        f().removeCallbacks(this.b);
    }

    public abstract void c();

    public final void d(long j) {
        b();
        if (j >= 0) {
            this.c = this.f10114a.zzav().currentTimeMillis();
            if (f().postDelayed(this.b, j)) {
                return;
            }
            this.f10114a.zzay().zzd().zzb("Failed to schedule delayed post. time", Long.valueOf(j));
        }
    }

    public final boolean e() {
        return this.c != 0;
    }

    public final Handler f() {
        Handler handler;
        if (d != null) {
            return d;
        }
        synchronized (f.class) {
            if (d == null) {
                d = new com.google.android.gms.internal.measurement.zzby(this.f10114a.zzau().getMainLooper());
            }
            handler = d;
        }
        return handler;
    }
}
