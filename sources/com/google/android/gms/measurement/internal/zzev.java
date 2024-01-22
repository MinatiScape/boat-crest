package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.util.Pair;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
/* loaded from: classes10.dex */
public final class zzev {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    public final String f10150a;
    public final String b;
    public final String c;
    public final long d;
    public final /* synthetic */ v e;

    public /* synthetic */ zzev(v vVar, String str, long j, zzeu zzeuVar) {
        this.e = vVar;
        Preconditions.checkNotEmpty("health_monitor");
        Preconditions.checkArgument(j > 0);
        this.f10150a = "health_monitor:start";
        this.b = "health_monitor:count";
        this.c = "health_monitor:value";
        this.d = j;
    }

    @WorkerThread
    public final long a() {
        return this.e.b().getLong(this.f10150a, 0L);
    }

    @WorkerThread
    public final void b() {
        this.e.zzg();
        long currentTimeMillis = this.e.zzs.zzav().currentTimeMillis();
        SharedPreferences.Editor edit = this.e.b().edit();
        edit.remove(this.b);
        edit.remove(this.c);
        edit.putLong(this.f10150a, currentTimeMillis);
        edit.apply();
    }

    @WorkerThread
    public final Pair<String, Long> zza() {
        long abs;
        this.e.zzg();
        this.e.zzg();
        long a2 = a();
        if (a2 == 0) {
            b();
            abs = 0;
        } else {
            abs = Math.abs(a2 - this.e.zzs.zzav().currentTimeMillis());
        }
        long j = this.d;
        if (abs < j) {
            return null;
        }
        if (abs > j + j) {
            b();
            return null;
        }
        String string = this.e.b().getString(this.c, null);
        long j2 = this.e.b().getLong(this.b, 0L);
        b();
        if (string != null && j2 > 0) {
            return new Pair<>(string, Long.valueOf(j2));
        }
        return v.w;
    }

    @WorkerThread
    public final void zzb(String str, long j) {
        this.e.zzg();
        if (a() == 0) {
            b();
        }
        if (str == null) {
            str = "";
        }
        long j2 = this.e.b().getLong(this.b, 0L);
        if (j2 <= 0) {
            SharedPreferences.Editor edit = this.e.b().edit();
            edit.putString(this.c, str);
            edit.putLong(this.b, 1L);
            edit.apply();
            return;
        }
        long nextLong = this.e.zzs.zzv().e().nextLong();
        long j3 = j2 + 1;
        SharedPreferences.Editor edit2 = this.e.b().edit();
        if ((Long.MAX_VALUE & nextLong) < Long.MAX_VALUE / j3) {
            edit2.putString(this.c, str);
        }
        edit2.putLong(this.b, j3);
        edit2.apply();
    }
}
