package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes10.dex */
public final class zzew {

    /* renamed from: a  reason: collision with root package name */
    public final String f10151a;
    public boolean b;
    public String c;
    public final /* synthetic */ v d;

    public zzew(v vVar, String str, String str2) {
        this.d = vVar;
        Preconditions.checkNotEmpty(str);
        this.f10151a = str;
    }

    @WorkerThread
    public final String zza() {
        if (!this.b) {
            this.b = true;
            this.c = this.d.b().getString(this.f10151a, null);
        }
        return this.c;
    }

    @WorkerThread
    public final void zzb(String str) {
        SharedPreferences.Editor edit = this.d.b().edit();
        edit.putString(this.f10151a, str);
        edit.apply();
        this.c = str;
    }
}
