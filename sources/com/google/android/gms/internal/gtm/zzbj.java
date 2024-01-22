package com.google.android.gms.internal.gtm;
/* loaded from: classes8.dex */
public final class zzbj implements Runnable {
    public final /* synthetic */ zzbq zza;

    public zzbj(zzbq zzbqVar, boolean z) {
        this.zza = zzbqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzck zzckVar;
        zzckVar = this.zza.zza;
        zzckVar.zzae();
    }
}
