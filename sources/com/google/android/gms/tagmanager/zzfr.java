package com.google.android.gms.tagmanager;

import com.google.android.gms.analytics.Logger;
/* loaded from: classes10.dex */
public final class zzfr implements Logger {
    @Override // com.google.android.gms.analytics.Logger
    public final void error(Exception exc) {
        zzdh.zzb("", exc);
    }

    @Override // com.google.android.gms.analytics.Logger
    public final int getLogLevel() {
        int i = zzdh.zza;
        if (i != 2) {
            if (i == 3 || i == 4) {
                return 1;
            }
            return i != 5 ? 3 : 2;
        }
        return 0;
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void info(String str) {
        zzdh.zzb.zzb(str);
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void setLogLevel(int i) {
        zzdh.zzc("GA uses GTM logger. Please use TagManager.setLogLevel(int) instead.");
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void verbose(String str) {
        zzdh.zzb.zzd(str);
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void warn(String str) {
        zzdh.zzc(str);
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void error(String str) {
        zzdh.zza(str);
    }
}
