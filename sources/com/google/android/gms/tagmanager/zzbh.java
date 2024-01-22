package com.google.android.gms.tagmanager;

import android.content.Context;
/* loaded from: classes10.dex */
public final class zzbh {
    public static zzbh zza;
    public static final Object zzb = new Object();
    public final zzec zzc;
    public final zzcc zzd;

    public zzbh(Context context) {
        zzcc zzb2 = zzcc.zzb(context);
        zzex zzexVar = new zzex();
        this.zzd = zzb2;
        this.zzc = zzexVar;
    }

    public static zzbh zzb(Context context) {
        zzbh zzbhVar;
        synchronized (zzb) {
            if (zza == null) {
                zza = new zzbh(context);
            }
            zzbhVar = zza;
        }
        return zzbhVar;
    }

    public final boolean zza(String str) {
        if (!this.zzc.zza()) {
            zzdh.zzc("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
            return false;
        }
        this.zzd.zzf(str, System.currentTimeMillis());
        return true;
    }
}
