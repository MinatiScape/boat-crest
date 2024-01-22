package com.google.android.gms.tagmanager;
/* loaded from: classes10.dex */
public final class zzfa implements Runnable {
    public final /* synthetic */ zzff zza;

    public zzfa(zzff zzffVar) {
        this.zza = zzffVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzcd zzcdVar;
        zzcdVar = this.zza.zzd;
        zzcdVar.zza();
    }
}
