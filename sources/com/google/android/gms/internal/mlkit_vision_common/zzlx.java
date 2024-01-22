package com.google.android.gms.internal.mlkit_vision_common;

import android.os.SystemClock;
import java.io.Closeable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes8.dex */
public class zzlx implements Closeable {
    public static final Map o = new HashMap();
    public final String h;
    public int i;
    public double j;
    public long k;
    public long l;
    public long m;
    public long n;

    public zzlx(String str) {
        this.m = 2147483647L;
        this.n = -2147483648L;
        this.h = str;
    }

    public static zzlx zze(String str) {
        w4 w4Var;
        zzmw.zza();
        if (!zzmw.zzb()) {
            w4Var = w4.p;
            return w4Var;
        }
        Map map = o;
        if (map.get("detectorTaskWithResource#run") == null) {
            map.put("detectorTaskWithResource#run", new zzlx("detectorTaskWithResource#run"));
        }
        return (zzlx) map.get("detectorTaskWithResource#run");
    }

    public final void a() {
        this.i = 0;
        this.j = 0.0d;
        this.k = 0L;
        this.m = 2147483647L;
        this.n = -2147483648L;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        long j = this.k;
        if (j != 0) {
            zzd(j);
            return;
        }
        throw new IllegalStateException("Did you forget to call start()?");
    }

    public zzlx zzb() {
        this.k = SystemClock.elapsedRealtimeNanos() / 1000;
        return this;
    }

    public void zzc(long j) {
        long elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos() / 1000;
        long j2 = this.l;
        if (j2 != 0 && elapsedRealtimeNanos - j2 >= 1000000) {
            a();
        }
        this.l = elapsedRealtimeNanos;
        this.i++;
        this.j += j;
        this.m = Math.min(this.m, j);
        this.n = Math.max(this.n, j);
        if (this.i % 50 == 0) {
            String.format(Locale.US, "[%s] cur=%dus, counts=%d, min=%dus, max=%dus, avg=%dus", this.h, Long.valueOf(j), Integer.valueOf(this.i), Long.valueOf(this.m), Long.valueOf(this.n), Integer.valueOf((int) (this.j / this.i)));
            zzmw.zza();
        }
        if (this.i % 500 == 0) {
            a();
        }
    }

    public void zzd(long j) {
        zzc((SystemClock.elapsedRealtimeNanos() / 1000) - j);
    }
}
