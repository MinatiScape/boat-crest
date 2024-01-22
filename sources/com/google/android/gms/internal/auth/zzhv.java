package com.google.android.gms.internal.auth;

import android.util.Base64;
/* loaded from: classes6.dex */
public final class zzhv implements zzht {
    public static final zzdc zza;
    public static final zzdc zzb;
    public static final zzdc zzc;
    public static final zzdc zzd;
    public static final zzdc zze;
    public static final zzdc zzf;
    public static final zzdc zzg;
    public static final zzdc zzh;
    public static final zzdc zzi;
    public static final zzdc zzj;
    public static final zzdc zzk;
    public static final zzdc zzl;
    public static final zzdc zzm;
    public static final zzdc zzn;

    static {
        zzcz zza2 = new zzcz(zzcr.zza("com.google.android.gms.auth_account")).zzb().zza();
        zza = zza2.zzc("getTokenRefactor__account_data_service_sample_percentage", 0.0d);
        zzb = zza2.zze("getTokenRefactor__account_data_service_tokenAPI_usable", true);
        zzc = zza2.zzd("getTokenRefactor__account_manager_timeout_seconds", 20L);
        zzd = zza2.zzd("getTokenRefactor__android_id_shift", 0L);
        zze = zza2.zze("getTokenRefactor__authenticator_logic_improved", false);
        try {
            zzf = zza2.zzf("getTokenRefactor__blocked_packages", zzhr.zzk(Base64.decode("ChNjb20uYW5kcm9pZC52ZW5kaW5nCiBjb20uZ29vZ2xlLmFuZHJvaWQuYXBwcy5tZWV0aW5ncwohY29tLmdvb2dsZS5hbmRyb2lkLmFwcHMubWVzc2FnaW5n", 3)), zzhu.zza);
            zzg = zza2.zze("getTokenRefactor__chimera_get_token_evolved", true);
            zzh = zza2.zzd("getTokenRefactor__clear_token_timeout_seconds", 20L);
            zzi = zza2.zzd("getTokenRefactor__default_task_timeout_seconds", 20L);
            zzj = zza2.zze("getTokenRefactor__gaul_accounts_api_evolved", false);
            zzk = zza2.zze("getTokenRefactor__gaul_token_api_evolved", false);
            zzl = zza2.zzd("getTokenRefactor__get_token_timeout_seconds", 120L);
            zzm = zza2.zze("getTokenRefactor__gms_account_authenticator_evolved", true);
            zzn = zza2.zzc("getTokenRefactor__gms_account_authenticator_sample_percentage", 0.0d);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    @Override // com.google.android.gms.internal.auth.zzht
    public final zzhr zza() {
        return (zzhr) zzf.zzb();
    }

    @Override // com.google.android.gms.internal.auth.zzht
    public final boolean zzb() {
        return ((Boolean) zzj.zzb()).booleanValue();
    }

    @Override // com.google.android.gms.internal.auth.zzht
    public final boolean zzc() {
        return ((Boolean) zzk.zzb()).booleanValue();
    }
}
