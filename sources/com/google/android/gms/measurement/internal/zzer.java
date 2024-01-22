package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes10.dex */
public final class zzer {

    /* renamed from: a  reason: collision with root package name */
    public final String f10147a;
    public final boolean b;
    public boolean c;
    public boolean d;
    public final /* synthetic */ v e;

    public zzer(v vVar, String str, boolean z) {
        this.e = vVar;
        Preconditions.checkNotEmpty(str);
        this.f10147a = str;
        this.b = z;
    }

    @WorkerThread
    public final void zza(boolean z) {
        SharedPreferences.Editor edit = this.e.b().edit();
        edit.putBoolean(this.f10147a, z);
        edit.apply();
        this.d = z;
    }

    @WorkerThread
    public final boolean zzb() {
        if (!this.c) {
            this.c = true;
            this.d = this.e.b().getBoolean(this.f10147a, this.b);
        }
        return this.d;
    }
}
