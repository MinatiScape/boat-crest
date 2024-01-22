package com.google.android.libraries.places.internal;

import androidx.annotation.Nullable;
import java.util.Locale;
/* loaded from: classes10.dex */
public final class zzdb implements zzda, zzds {
    @Nullable
    private volatile String zza;
    @Nullable
    private volatile Locale zzb;
    private volatile boolean zzc;

    public final synchronized void zza(String str, @Nullable Locale locale, boolean z) {
        zzft.zza(str, "API Key must not be null.");
        zzft.zza(!str.isEmpty(), "API Key must not be empty.");
        this.zza = str;
        this.zzb = locale;
        this.zzc = false;
    }

    @Override // com.google.android.libraries.places.internal.zzda, com.google.android.libraries.places.internal.zzds
    public final synchronized Locale zzb() {
        zzft.zzb(zzd(), "ApiConfig must be initialized.");
        if (this.zzb == null) {
            return Locale.getDefault();
        }
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.internal.zzda
    public final boolean zzc() {
        return this.zzc;
    }

    public final synchronized boolean zzd() {
        return this.zza != null;
    }

    public final synchronized void zze() {
        this.zza = null;
        this.zzb = null;
    }

    @Override // com.google.android.libraries.places.internal.zzda
    public final synchronized String zza() {
        zzft.zzb(zzd(), "ApiConfig must be initialized.");
        return this.zza;
    }
}
