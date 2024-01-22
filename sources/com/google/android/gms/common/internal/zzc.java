package com.google.android.gms.common.internal;

import android.util.Log;
import androidx.annotation.Nullable;
import java.util.ArrayList;
/* loaded from: classes6.dex */
public abstract class zzc {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Object f8344a;
    public boolean b = false;
    public final /* synthetic */ BaseGmsClient c;

    public zzc(BaseGmsClient baseGmsClient, Object obj) {
        this.c = baseGmsClient;
        this.f8344a = obj;
    }

    public abstract void zza(Object obj);

    public abstract void zzc();

    public final void zze() {
        Object obj;
        synchronized (this) {
            obj = this.f8344a;
            if (this.b) {
                String obj2 = toString();
                Log.w("GmsClient", "Callback proxy " + obj2 + " being reused. This is not safe.");
            }
        }
        if (obj != null) {
            try {
                zza(obj);
            } catch (RuntimeException e) {
                throw e;
            }
        }
        synchronized (this) {
            this.b = true;
        }
        zzg();
    }

    public final void zzf() {
        synchronized (this) {
            this.f8344a = null;
        }
    }

    public final void zzg() {
        ArrayList arrayList;
        ArrayList arrayList2;
        zzf();
        arrayList = this.c.x;
        synchronized (arrayList) {
            arrayList2 = this.c.x;
            arrayList2.remove(this);
        }
    }
}
