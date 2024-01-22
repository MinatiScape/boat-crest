package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes10.dex */
public final class z2 implements Runnable {
    public final /* synthetic */ AtomicReference h;
    public final /* synthetic */ String i;
    public final /* synthetic */ String j;
    public final /* synthetic */ zzp k;
    public final /* synthetic */ boolean l;
    public final /* synthetic */ zzjj m;

    public z2(zzjj zzjjVar, AtomicReference atomicReference, String str, String str2, String str3, zzp zzpVar, boolean z) {
        this.m = zzjjVar;
        this.h = atomicReference;
        this.i = str2;
        this.j = str3;
        this.k = zzpVar;
        this.l = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AtomicReference atomicReference;
        zzdz zzdzVar;
        synchronized (this.h) {
            try {
                zzdzVar = this.m.c;
            } catch (RemoteException e) {
                this.m.zzs.zzay().zzd().zzd("(legacy) Failed to get user properties; remote exception", null, this.i, e);
                this.h.set(Collections.emptyList());
                atomicReference = this.h;
            }
            if (zzdzVar == null) {
                this.m.zzs.zzay().zzd().zzd("(legacy) Failed to get user properties; not connected to service", null, this.i, this.j);
                this.h.set(Collections.emptyList());
                this.h.notify();
                return;
            }
            if (TextUtils.isEmpty(null)) {
                Preconditions.checkNotNull(this.k);
                this.h.set(zzdzVar.zzh(this.i, this.j, this.l, this.k));
            } else {
                this.h.set(zzdzVar.zzi(null, this.i, this.j, this.l));
            }
            this.m.g();
            atomicReference = this.h;
            atomicReference.notify();
        }
    }
}
