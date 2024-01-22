package com.google.android.libraries.places.internal;

import android.content.Context;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes10.dex */
public abstract class zzdj {

    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class zza {
        public abstract zza zza(int i);

        public abstract zza zza(zzb zzbVar);

        public abstract zza zza(String str);

        public abstract zzdj zza();

        public final zzdj zzb() {
            zzdj zza = zza();
            zzft.zzb(!zza.zza().isEmpty(), "Package name must not be empty.");
            return zza;
        }
    }

    /* loaded from: classes10.dex */
    public enum zzb {
        PROGRAMMATIC_API,
        AUTOCOMPLETE_WIDGET
    }

    public static zza zza(Context context) {
        zzdd zzddVar = new zzdd(context);
        return new zzdh().zza(zzddVar.zza()).zza(zzddVar.zzb()).zza(zzb.PROGRAMMATIC_API);
    }

    public abstract String zza();

    public abstract int zzb();

    public abstract zzb zzc();
}
