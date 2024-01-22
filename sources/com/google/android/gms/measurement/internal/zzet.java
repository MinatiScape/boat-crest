package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes10.dex */
public final class zzet {

    /* renamed from: a  reason: collision with root package name */
    public final String f10149a;
    public final long b;
    public boolean c;
    public long d;
    public final /* synthetic */ v e;

    public zzet(v vVar, String str, long j) {
        this.e = vVar;
        Preconditions.checkNotEmpty(str);
        this.f10149a = str;
        this.b = j;
    }

    @WorkerThread
    public final long zza() {
        if (!this.c) {
            this.c = true;
            this.d = this.e.b().getLong(this.f10149a, this.b);
        }
        return this.d;
    }

    @WorkerThread
    public final void zzb(long j) {
        SharedPreferences.Editor edit = this.e.b().edit();
        edit.putLong(this.f10149a, j);
        edit.apply();
        this.d = j;
    }
}
